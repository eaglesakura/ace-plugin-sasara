package org.andriders.talk.sasara.service;

import com.eaglesakura.andriders.central.CentralDataHandler;
import com.eaglesakura.andriders.central.CentralNotificationHandler;
import com.eaglesakura.andriders.command.CommandKey;
import com.eaglesakura.andriders.plugin.AcePluginService;
import com.eaglesakura.andriders.plugin.Category;
import com.eaglesakura.andriders.plugin.DisplayKey;
import com.eaglesakura.andriders.plugin.PluginInformation;
import com.eaglesakura.andriders.plugin.connection.PluginConnection;
import com.eaglesakura.andriders.serialize.NotificationProtocol;
import com.eaglesakura.andriders.serialize.RawCentralData;
import com.eaglesakura.android.framework.delegate.lifecycle.ServiceLifecycleDelegate;
import com.eaglesakura.android.framework.util.AppSupportUtil;
import com.eaglesakura.android.garnet.Garnet;
import com.eaglesakura.android.garnet.Inject;
import com.eaglesakura.android.rx.CallbackTime;
import com.eaglesakura.android.rx.ExecuteTarget;
import com.eaglesakura.android.rx.error.TaskCanceledException;
import com.eaglesakura.lambda.CancelCallback;
import com.eaglesakura.material.widget.support.SupportCancelCallbackBuilder;
import com.eaglesakura.thread.Holder;
import com.eaglesakura.util.StringUtil;
import com.eaglesakura.util.Util;

import org.andriders.talk.sasara.data.SoundDataManager;
import org.andriders.talk.sasara.data.sound.SoundSource;
import org.andriders.talk.sasara.data.sound.SoundSourceCollection;
import org.andriders.talk.sasara.error.AppException;
import org.andriders.talk.sasara.provider.AppManagerProvider;
import org.andriders.talk.sasara.util.AppLog;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;

import java.util.ArrayList;
import java.util.List;

/**
 * 音声出力を行うPlugin
 */
public class SoundPluginService extends Service implements AcePluginService {
    ServiceLifecycleDelegate mLifecycleDelegate = new ServiceLifecycleDelegate();

    @Inject(AppManagerProvider.class)
    SoundDataManager mSoundDataManager;

    /**
     * 再生可能な音声一覧
     */
    SoundSourceCollection mSourceCollection;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        AppLog.system("onBind(%s)", toString());
        PluginConnection session = PluginConnection.onBind(this, intent);
        if (session == null) {
            return null;
        }

        return session.getBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        AppLog.system("onUnbind(%s)", toString());
        PluginConnection.onUnbind(this, intent);
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Garnet.create(this)
                .depend(Context.class, this)
                .inject();

        try {
            mSourceCollection = mSoundDataManager.loadSoundInfo();
        } catch (AppException e) {
            AppLog.report(e);
            mSourceCollection = new SoundSourceCollection(new ArrayList<>());
        }
    }

    @Override
    public PluginInformation getExtensionInformation(PluginConnection connection) {
        PluginInformation info = new PluginInformation(this, "sound");
        info.setActivated(true);
        info.setCategory(Category.CATEGORY_OTHERS);
        info.setHasSetting(false);
        info.setSummary("A.C.E.の通知を「さとうささら」が音声でお知らせします。");
        return info;
    }

    @Override
    public List<DisplayKey> getDisplayInformation(PluginConnection connection) {
        return new ArrayList<>();
    }

    @Override
    public void onAceServiceConnected(PluginConnection connection) {
        mLifecycleDelegate = new ServiceLifecycleDelegate();
        mLifecycleDelegate.onCreate();

        connection.getCentralDataReceiver().addHandler(mNotificationHandler);
        connection.getCentralDataReceiver().addHandler(mCentralDataHandler);
    }

    @Override
    public void onAceServiceDisconnected(PluginConnection connection) {
        mLifecycleDelegate.onDestroy();
        mLifecycleDelegate = null;
    }

    @Override
    public void onEnable(PluginConnection connection) {

    }

    @Override
    public void onDisable(PluginConnection connection) {

    }

    @Override
    public void startSetting(PluginConnection connection) {

    }

    CentralNotificationHandler mNotificationHandler = new CentralNotificationHandler() {
        @Override
        public void onReceivedNotification(@NonNull NotificationProtocol.RawNotification notification, @Nullable RawCentralData centralData) {
            AppLog.system("Received Notification id[%s]", notification.uniqueId);
            SoundSource sound = mSourceCollection.findFromNotificationId(notification.uniqueId);
            play(sound);
        }

        @Override
        public void onReceivedCommandBoot(@NonNull CommandKey key, @Nullable RawCentralData centralData) {
            if (key.toString().startsWith("proximity#")) {
                SoundSource sound = mSourceCollection.find(src -> src.getSoundKey().equals("aces-command-proximity"));
                play(sound);
            }
        }
    };

    CentralDataHandler mCentralDataHandler = new CentralDataHandler() {
        int mLastDistance = -1;

        @Override
        public void onReceived(RawCentralData newData) {
            int distance = (int) (newData.session.distanceKm / 10);
            if (mLastDistance != distance) {
                mLastDistance = distance;
                SoundSource source = mSourceCollection.find(
                        src -> src.getSoundKey().equals(StringUtil.format("aces-activity-move-%d0km", distance))
                );
                play(source);
            }
        }
    };

    /**
     * 音源再生を行う
     */
    @UiThread
    void play(SoundSource source) {
        if (source == null) {
            return;
        }

        AppLog.system("Play Request %s", source.toString());

        mLifecycleDelegate.async(ExecuteTarget.LocalQueue, CallbackTime.Alive, task -> {
            String path = StringUtil.format("talk/%s", source.getSoundKey());
            playSound(this, path, SupportCancelCallbackBuilder.from(task).build());
            return this;
        }).failed((error, task) -> {
            AppLog.printStackTrace(error);
        }).start();
    }

    /**
     * サウンドを一度だけ鳴らし、再生完了まで待つ
     *
     * @param context    app context
     * @param assetsPath アセットパス
     */
    public static boolean playSound(Context context, String assetsPath, CancelCallback cancelCallback) throws TaskCanceledException {
        final MediaPlayer player = new MediaPlayer();
        Holder<Boolean> holder = new Holder<>();

        try {
            if (!assetsPath.endsWith(".ogg")) {
                assetsPath += ".ogg";
            }

            AssetFileDescriptor fd = context.getResources().getAssets().openFd(assetsPath);
            player.setOnCompletionListener(mp -> holder.set(Boolean.TRUE));
            player.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(), fd.getLength());
            player.setAudioStreamType(AudioManager.STREAM_NOTIFICATION);
            player.prepare();
            player.start();

            while (Boolean.TRUE != holder.get()) {
                Util.sleep(1);
                AppSupportUtil.assertNotCanceled(cancelCallback);
            }

            return true;
        } catch (TaskCanceledException e) {
            throw e;
        } catch (Exception e) {
            AppLog.printStackTrace(e);
            return false;
        } finally {
            if (player != null) {
                player.release();
            }
        }
    }

}

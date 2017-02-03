package org.andriders.talk.sasara.data;

import com.eaglesakura.android.garnet.Singleton;
import com.eaglesakura.json.JSON;
import com.eaglesakura.util.CollectionUtil;

import org.andriders.talk.sasara.data.sound.SoundInfo;
import org.andriders.talk.sasara.data.sound.SoundSource;
import org.andriders.talk.sasara.data.sound.SoundSourceCollection;
import org.andriders.talk.sasara.error.AppException;
import org.andriders.talk.sasara.error.io.AppIOException;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * 音声情報の管理を行う
 */
@Singleton
public class SoundDataManager extends AppDataManager {
    public SoundDataManager(Context context) {
        super(context);
    }

    public SoundSourceCollection loadSoundInfo() throws AppException {
        try (InputStream is = getContext().getResources().getAssets().open("talk/info.json")) {
            SoundInfo info = JSON.decode(is, SoundInfo.class);
            return new SoundSourceCollection(CollectionUtil.asOtherList(info.sounds, it -> new SoundSource(it)));
        } catch (IOException e) {
            throw new AppIOException(e);
        }
    }
}

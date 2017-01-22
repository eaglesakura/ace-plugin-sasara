package org.andriders.talk.sasara.ui.navigation.example;

import com.eaglesakura.android.framework.delegate.fragment.SupportFragmentDelegate;
import com.eaglesakura.android.framework.ui.progress.ProgressToken;
import com.eaglesakura.android.framework.ui.support.annotation.FragmentLayout;
import com.eaglesakura.android.garnet.Inject;
import com.eaglesakura.android.margarine.Bind;
import com.eaglesakura.android.margarine.OnCheckedChanged;

import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;

import org.andriders.talk.sasara.R;
import org.andriders.talk.sasara.data.setting.AppSettings;
import org.andriders.talk.sasara.provider.AppContextProvider;
import org.andriders.talk.sasara.ui.navigation.base.AppNavigationFragment;
import org.andriders.talk.sasara.util.AppLog;

/**
 * サンプル画面
 */
@FragmentLayout(R.layout.example_helloworld)
public class ExampleFragmentMain extends AppNavigationFragment {

    /**
     * Frameworkにより自動的に依存注入が行われる
     */
    @Inject(AppContextProvider.class)
    AppSettings mAppSettings;

    /**
     * Frameworkにより自動的にViewBindが行われる
     */
    @Bind(R.id.Example_Item_Debuggable)
    SwitchCompat mDebugSwitch;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 非同期処理を行なう
        asyncUI(task -> {
            try (ProgressToken token = pushProgress(R.string.EsMaterial_Word_Common_DataLoad)) {
                // 適当な非同期処理
                // ダミーで5秒間スリープ
                task.waitTime(1000 * 5);
                return this;
            }
        }).completed((result, task) -> {
            AppLog.system("Hello World!!");
        }).start();
    }

    @Override
    public void onAfterViews(SupportFragmentDelegate self, int flags) {
        super.onAfterViews(self, flags);
        mDebugSwitch.setChecked(mAppSettings.getDebugSetting().isDebugEnable());
    }

    /**
     * Frameworkにより自動的にOnCheckedChangeが行われる。
     */
    @OnCheckedChanged(R.id.Example_Item_Debuggable)
    void changedDebuggable(boolean checked) {
        mAppSettings.getDebugSetting().setDebugEnable(checked);
        mAppSettings.commit();
    }
}

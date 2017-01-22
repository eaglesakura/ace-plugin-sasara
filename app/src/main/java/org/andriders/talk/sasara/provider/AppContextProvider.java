package org.andriders.talk.sasara.provider;

import com.eaglesakura.android.framework.provider.ContextProvider;
import com.eaglesakura.android.garnet.Provide;
import com.eaglesakura.android.garnet.Singleton;

import org.andriders.talk.sasara.CustomApplication;
import org.andriders.talk.sasara.data.setting.AppSettings;


/**
 * アプリ設定等のグローバルリソースにアクセスする
 */
@Singleton
public class AppContextProvider extends ContextProvider {

    @Provide
    public CustomApplication provideCustomApplication() {
        return (CustomApplication) getApplication();
    }

    @Provide
    public AppSettings provideAppSettings() {
        return new AppSettings.Builder(getContext()).build();
    }
}

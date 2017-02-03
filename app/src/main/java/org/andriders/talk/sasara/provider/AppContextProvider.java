package org.andriders.talk.sasara.provider;

import com.eaglesakura.android.framework.provider.ContextProvider;
import com.eaglesakura.android.garnet.Provide;
import com.eaglesakura.android.garnet.Singleton;

import org.andriders.talk.sasara.CustomApplication;


/**
 * アプリ設定等のグローバルリソースにアクセスする
 */
@Singleton
public class AppContextProvider extends ContextProvider {

    @Provide
    public CustomApplication provideCustomApplication() {
        return (CustomApplication) getApplication();
    }

}

package org.andriders.talk.sasara.provider;

import com.eaglesakura.android.framework.provider.ContextProvider;
import com.eaglesakura.android.garnet.Provide;
import com.eaglesakura.android.garnet.Singleton;

import org.andriders.talk.sasara.data.storage.AppStorageManager;

/**
 * アプリ内での各種Controllerを依存解決するProvider
 */
@Singleton
public class AppStorageProvider extends ContextProvider {
    @Provide
    public AppStorageManager provideStorageController() {
        return new AppStorageManager(getApplication());
    }
}

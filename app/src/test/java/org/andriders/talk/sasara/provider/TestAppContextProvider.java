package org.andriders.talk.sasara.provider;

import org.andriders.talk.sasara.data.setting.AppSettings;

public class TestAppContextProvider extends AppContextProvider {
    @Override
    public AppSettings provideAppSettings() {
        AppSettings settings = new AppSettings.Builder(getContext())
                .store(AppSettings.newDatabasePropertyStore(getContext()))
                .build();
        return settings;
    }
}

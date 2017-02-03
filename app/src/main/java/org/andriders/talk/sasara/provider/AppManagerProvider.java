package org.andriders.talk.sasara.provider;

import com.eaglesakura.android.framework.provider.ContextProvider;
import com.eaglesakura.android.garnet.Provide;
import com.eaglesakura.android.garnet.Singleton;

import org.andriders.talk.sasara.data.SoundDataManager;

@Singleton
public class AppManagerProvider extends ContextProvider {
    @Provide
    public SoundDataManager provideSoundDataManager() {
        return new SoundDataManager(getContext());
    }
}

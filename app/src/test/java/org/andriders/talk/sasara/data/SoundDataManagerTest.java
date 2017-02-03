package org.andriders.talk.sasara.data;

import com.eaglesakura.android.garnet.Garnet;

import org.junit.Test;

import org.andriders.talk.sasara.AppUnitTestCase;
import org.andriders.talk.sasara.data.sound.SoundSourceCollection;
import org.andriders.talk.sasara.provider.AppManagerProvider;
import org.andriders.talk.sasara.util.AppLog;

public class SoundDataManagerTest extends AppUnitTestCase {

    @Test
    public void サウンドリストを読み込める() throws Throwable {
        SoundDataManager instance = Garnet.instance(AppManagerProvider.class, SoundDataManager.class);
        assertNotNull(instance);

        SoundSourceCollection soundSource = instance.loadSoundInfo();
        assertNotNull(soundSource);

        validate(soundSource.list()).notEmpty().allNotNull()
                .each(sound -> {
                    assertNotEmpty(sound.getName());
                    assertNotEmpty(sound.getSoundKey());

                    AppLog.test("Sound[%s]", sound.toString());
                });
    }
}
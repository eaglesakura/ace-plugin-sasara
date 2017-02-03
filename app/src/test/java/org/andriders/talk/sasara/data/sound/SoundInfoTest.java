package org.andriders.talk.sasara.data.sound;

import com.eaglesakura.json.JSON;

import org.junit.Test;

import org.andriders.talk.sasara.AppUnitTestCase;

import java.io.InputStream;

public class SoundInfoTest extends AppUnitTestCase {

    @Test
    public void JSONがパースできる() throws Throwable {
        try (InputStream is = getContext().getAssets().open("talk/info.json")) {
            SoundInfo info = JSON.decode(is, SoundInfo.class);

            assertNotEmpty(info.id);
            assertNotEmpty(info.name);
            validate(info.sounds).allNotNull().notEmpty()
                    .each(sound -> {
                        assertNotEmpty(sound.name);
                        assertNotEmpty(sound.soundKey);
                        if (sound.notificationKey != null) {
                            assertNotEmpty(sound.notificationKey);
                        }
                    });
        }
    }
}
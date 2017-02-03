package org.andriders.talk.sasara.service;

import org.junit.Test;

import org.andriders.talk.sasara.AppDeviceTestCase;

public class SoundPluginServiceTest extends AppDeviceTestCase {

    @Test
    public void サウンドが再生できる() throws Throwable {
        assertTrue(SoundPluginService.playSound(getContext(), "talk/aces-system-boot-ace-service", () -> false));
    }
}
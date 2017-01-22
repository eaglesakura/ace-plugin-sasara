package org.andriders.talk.sasara.data.setting;

import com.eaglesakura.android.garnet.Garnet;

import org.junit.Test;

import org.andriders.talk.sasara.AppDeviceTestCase;
import org.andriders.talk.sasara.provider.AppContextProvider;

public class AppSettingsTest extends AppDeviceTestCase {

    @Test
    public void インスタンスが生成できる() throws Throwable {
        AppSettings instance = Garnet.instance(AppContextProvider.class, AppSettings.class);
        assertNotNull(instance);
        assertNotNull(instance.getDebugSetting());
        assertFalse(instance.getDebugSetting().isDebugEnable());
    }
}
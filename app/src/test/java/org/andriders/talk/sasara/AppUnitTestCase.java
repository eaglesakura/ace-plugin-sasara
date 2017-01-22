package org.andriders.talk.sasara;

import com.eaglesakura.android.AndroidSupportTestCase;
import com.eaglesakura.android.garnet.Garnet;

import org.robolectric.annotation.Config;

import org.andriders.talk.sasara.provider.AppContextProvider;
import org.andriders.talk.sasara.provider.TestAppContextProvider;
import org.andriders.talk.sasara.util.AppLog;

import org.andriders.talk.sasara.BuildConfig;

@Config(constants = BuildConfig.class, packageName = BuildConfig.APPLICATION_ID, sdk = 23)
public abstract class AppUnitTestCase extends AndroidSupportTestCase {

    @Override
    public void onSetup() {
        super.onSetup();

        // UnitTest用モジュールへ切り替える
        Garnet.override(AppContextProvider.class, TestAppContextProvider.class);

        AppLog.test("UnitTest Database[%s]", getApplication().getFilesDir().getAbsolutePath());
    }

}

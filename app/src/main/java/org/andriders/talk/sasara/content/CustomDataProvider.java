package org.andriders.talk.sasara.content;

import com.eaglesakura.android.framework.content.ApplicationDataProvider;

import org.andriders.talk.sasara.util.AppLog;

import android.content.Context;
import android.net.Uri;

/**
 * アプリ用のデータハンドリングを行なう
 */
public class CustomDataProvider extends ApplicationDataProvider {

    @Override
    public boolean onCreate() {
        // プロパティハンドリングを追加する
//        {
//            PropertyProviderHandler handler = new PropertyProviderHandler(getContext(), getPropertyUri(getContext()));
//            handler.addStore(PROPERTY_STOREKEY_APPSETTINGS, AppSettings.newDatabasePropertyStore(getContext()));
//
//            addHandler(handler);
//        }

        AppLog.system("DataProvider.onCreate");
        return true;
    }

    @Override
    public void shutdown() {
        AppLog.system("DataProvider.shutdown");
        super.shutdown();
    }

    public static final String PROPERTY_STOREKEY_APPSETTINGS = "PROPERTY_STOREKEY_APPSETTINGS";

    public static Uri getPropertyUri(Context context) {
        return Uri.parse("content://" + context.getPackageName() + "/property");
    }
}

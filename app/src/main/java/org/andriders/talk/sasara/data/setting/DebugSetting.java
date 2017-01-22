package org.andriders.talk.sasara.data.setting;

import android.content.Context;

import org.andriders.talk.sasara.gen.prop.DebugProps;

/**
 * 開発設定を保持する
 */
public class DebugSetting {
    final Context mContext;

    final DebugProps mProps;

    public DebugSetting(Context context, DebugProps props) {
        mContext = context;
        mProps = props;
    }

    public void setDebugEnable(boolean set) {
        mProps.setDebugEnable(set);
    }

    public boolean isDebugEnable() {
        return mProps.isDebugEnable();
    }
}

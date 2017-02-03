package org.andriders.talk.sasara.data;

import com.eaglesakura.android.garnet.Initializer;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * アプリデータ管理用Managerの基底クラス
 *
 * Read/Write等の処理は内部で隠蔽することで、利用側とDBを切り離す。
 */
public abstract class AppDataManager {

    private Context mContext;

    /**
     * sync管理
     * <p>
     * データは複数スレッドから呼び出されるため、ロックを行ってオブジェクトがコンフリクトしないようにする。
     */
    @NonNull
    private final Object lock = new Object();


    public AppDataManager(Context context) {
        mContext = context;
    }

    @Initializer
    public void initialize() {
//        Garnet.create(this)
//                .depend(Context.class, mContext)
//                .inject();
    }

    public Context getContext() {
        return mContext;
    }

}

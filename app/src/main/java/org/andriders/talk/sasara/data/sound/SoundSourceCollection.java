package org.andriders.talk.sasara.data.sound;

import com.eaglesakura.collection.DataCollection;

import android.support.annotation.Nullable;

import java.util.List;

public class SoundSourceCollection extends DataCollection<SoundSource> {
    public SoundSourceCollection(List<SoundSource> dataList) {
        super(dataList);
    }

    /**
     * 通知IDからサウンドを検索する
     */
    @Nullable
    public SoundSource findFromNotificationId(String notificationId) {
        return find(src -> notificationId.equals(src.getNotificationKey()));
    }
}

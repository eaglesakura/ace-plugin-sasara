package org.andriders.talk.sasara.data.sound;

import android.support.annotation.NonNull;

/**
 * 音声の1データを管理する
 */
public class SoundSource {
    @NonNull
    final SoundInfo.Sound mRaw;

    public SoundSource(@NonNull SoundInfo.Sound raw) {
        mRaw = raw;
    }

    public String getName() {
        return mRaw.name;
    }

    public String getNotificationKey() {
        return mRaw.notificationKey;
    }

    public String getSoundKey() {
        return mRaw.soundKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SoundSource that = (SoundSource) o;

        return getSoundKey().equals(that.getSoundKey());

    }

    @Override
    public int hashCode() {
        return getSoundKey().hashCode();
    }

    @Override
    public String toString() {
        return getName() + "/" + getSoundKey() + "/" + getNotificationKey();
    }
}

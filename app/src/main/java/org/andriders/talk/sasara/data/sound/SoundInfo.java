package org.andriders.talk.sasara.data.sound;

import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * info.json parse
 */
@Keep
public class SoundInfo {

    @NonNull
    public String id;

    @NonNull
    public String name;

    @NonNull
    public List<Sound> sounds = new ArrayList<>();

    @Keep
    public static class Sound {
        @NonNull
        public String name;

        @Nullable
        public String notificationKey;

        @NonNull
        public String soundKey;
    }
}

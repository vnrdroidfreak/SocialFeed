package com.virudhairaj.feedsdk.abstracts;

import android.support.annotation.NonNull;

/**
 * Generic Audio actions
 * for customization, extend this and create your own actions
 */

public interface IAudioAction<T> {

    public void onTogglePlayback(@NonNull T item, int position, boolean isPlay, Object... extras);

    public void onClickRepeat(@NonNull T item, int position, boolean isRepeat, Object... extras);
}

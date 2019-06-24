package com.virudhairaj.feedsdk.abstracts;

import android.support.annotation.NonNull;

/**
 * Generic Comment actions
 * for customization, extend this and create your own actions
 */

public interface ICommentAction<T> {

    public void onClickEdit(@NonNull T item, int position, Object... extras);

    public <LikeType> void onClickLike(@NonNull T item, int position, @NonNull LikeType likeType, Object... extras);

    public void onClickDelete(@NonNull T item, int position, Object... extras);

    public void onClickReport(@NonNull T item, int position, Object... extras);

    public void onClickReply(@NonNull T item, int position, Object... extras);

    public void onClickViewAllReply(@NonNull T item, int position, Object... extras);

    public void onClickLikerList(@NonNull T item, int position, Object... extras);
}

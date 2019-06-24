package com.virudhairaj.feedsdk.abstracts;

import android.support.annotation.NonNull;

/**
 * Generic Feed actions
 * for customization, extend this and create your own actions
 */

public interface IFeedAction<Item> {

    public <Type> void onClickShare(@NonNull Item item, int position, @NonNull Type shareType, Object... extras);

    public <Type> void onClickLike(@NonNull Item item, int position, @NonNull Type likeType, Object... extras);

    public void onClickLikerList(@NonNull Item item, int position, Object... extras);

    public void onClickDelete(@NonNull Item item, int position, Object... extras);

    public void onClickReport(@NonNull Item item, int position, Object... extras);

    public void onClickComment(@NonNull Item item, int position, Object... extras);

    public void onClickViewComments(@NonNull Item item, int position, Object... extras);

    public void onClickEdit(@NonNull Item item, int position, Object... extras);

}

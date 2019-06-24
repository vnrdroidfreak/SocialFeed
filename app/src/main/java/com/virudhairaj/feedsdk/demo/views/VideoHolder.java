package com.virudhairaj.feedsdk.demo.views;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

import com.virudhairaj.feedcore.AbsFeedHolder;
import com.virudhairaj.feedcore.LayoutResource;
import com.virudhairaj.feedsdk.demo.R;
import com.virudhairaj.feedsdk.demo.model.VideoFeed;

@LayoutResource(resourceId = R.layout.layout_feed_video)
public class VideoHolder extends AbsFeedHolder<VideoFeed> {

    public VideoHolder(@NonNull Context context, int layoutResId, @NonNull ViewGroup parent, boolean attachToRoot) {
        super(context, layoutResId, parent, attachToRoot);
    }

    @Override
    public void onItemBind(@NonNull VideoFeed feed, int position, @Nullable Object... extras) {

    }


    public void onTogglePlayback(@NonNull VideoFeed item, int position, boolean isPlay, Object... extras) {

    }

    public void onClickRepeat(@NonNull VideoFeed item, int position, boolean isRepeat, Object... extras) {

    }

}

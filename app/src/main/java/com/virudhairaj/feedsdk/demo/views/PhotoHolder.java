package com.virudhairaj.feedsdk.demo.views;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

import com.virudhairaj.feedcore.AbsFeedHolder;
import com.virudhairaj.feedcore.LayoutResource;
import com.virudhairaj.feedsdk.demo.R;
import com.virudhairaj.feedsdk.demo.model.PhotoFeed;

@LayoutResource(resourceId = R.layout.layout_feed_photo)
public class PhotoHolder extends AbsFeedHolder<PhotoFeed> {

    public PhotoHolder(@NonNull Context context, int layoutResId, @NonNull ViewGroup parent, boolean attachToRoot) {
        super(context, layoutResId, parent, attachToRoot);
    }

    @Override
    public void onItemBind(@NonNull PhotoFeed feed, int position, @Nullable Object... extras) {
        super.onItemBind(feed, position, extras);
    }
}

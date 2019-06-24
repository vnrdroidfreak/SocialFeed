package com.virudhairaj.feedsdk.demo.model;

import android.support.annotation.Nullable;

import com.virudhairaj.feedsdk.demo.states.FeedType;

public class PhotoFeed extends Feed {

    @Nullable
    protected String photoLink;

    public PhotoFeed() {
        super(FeedType.PHOTO);
    }

    @Nullable
    public String getPhotoLink() {
        return photoLink != null && !photoLink.isEmpty() ? photoLink : "no-data-found";
    }

    public void setPhotoLink(@Nullable String photoLink) {
        this.photoLink = photoLink;
    }

}

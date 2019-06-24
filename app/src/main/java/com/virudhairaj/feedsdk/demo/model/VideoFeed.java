package com.virudhairaj.feedsdk.demo.model;

import android.support.annotation.Nullable;

import com.virudhairaj.feedsdk.demo.states.FeedType;

import java.util.ArrayList;

public class VideoFeed extends Feed {

    @Nullable
    protected String videoLink;

    @Nullable
    protected String thumbLink;

    public VideoFeed() {
        super(FeedType.VIDEO);
    }

    @Nullable
    public String getVideoLink() {
        return videoLink != null && !videoLink.isEmpty() ? videoLink : "no-data-found";
    }

    public void setVideoLink(@Nullable String videoLink) {
        this.videoLink = videoLink;
    }

    @Nullable
    public String getThumbLink() {
        return thumbLink != null && !thumbLink.isEmpty() ? thumbLink : "no-data-found";
    }

    public void setThumbLink(@Nullable String photoUrl) {
        this.thumbLink = photoUrl;
    }


    public static ArrayList<VideoFeed> getSampleData(){
        ArrayList<VideoFeed> feeds=new ArrayList<>();


        for (int i=0;i<10;i++){
            VideoFeed feed=new VideoFeed();
            feed.videoLink="https://www.sample-videos.com/video/mp4/240/big_buck_bunny_240p_1mb.mp4";
            feeds.add(feed);
        }
        return  feeds;
    }
}

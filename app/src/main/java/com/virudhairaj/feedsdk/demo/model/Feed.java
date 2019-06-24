package com.virudhairaj.feedsdk.demo.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.virudhairaj.feedsdk.demo.states.FeedType;

import com.virudhairaj.feedcore.AbsFeed;

/**
 * this is root class of all type of feed model
 */
public class Feed extends AbsFeed {

    @NonNull
    protected String id = ""; //for feed id

    @Nullable
    protected String title;

    @Nullable
    protected String description;

    @Nullable
    protected String feedLink;

    @Nullable
    protected Integer likesCount;

    @Nullable
    protected Integer commentCount;

    public Feed() {
        super(FeedType.EMPTY);
    }

    public Feed(@NonNull FeedType feedType) {
        super(feedType);
    }

    @Nullable
    public Integer getLikesCount() {
        return likesCount != null ? likesCount : 0;
    }

    @Nullable
    public Integer getCommentCount() {
        return commentCount != null ? commentCount : 0;
    }

    @Nullable
    public String getFeedLink() {
        return feedLink != null ? feedLink : "";
    }

    public void setLikesCount(@Nullable Integer likesCount) {
        this.likesCount = likesCount;
    }

    public void setLikesCount(@Nullable String likesCountStr) {
        try {
            this.likesCount = Integer.parseInt(likesCountStr);
        } catch (Exception e) {

        }
    }

    public void setCommentCount(@Nullable Integer commentCount) {
        this.commentCount = commentCount;
    }

    public void setCommentCount(@Nullable String commentCountStr) {
        try {
            this.commentCount = Integer.parseInt(commentCountStr);
        } catch (Exception e) {

        }
    }





    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    public void setTitle(@Nullable String title) {
        this.title = title;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    public void setFeedLink(@Nullable String feedLink) {
        this.feedLink = feedLink;
    }
}

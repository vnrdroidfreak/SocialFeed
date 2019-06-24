package com.virudhairaj.feedsdk.demo.states;

import android.support.annotation.NonNull;

import com.virudhairaj.feedcore.AbsFeedHolder;
import com.virudhairaj.feedcore.IType;
import com.virudhairaj.feedsdk.demo.views.EmptyHolder;
import com.virudhairaj.feedsdk.demo.views.PhotoHolder;
import com.virudhairaj.feedsdk.demo.views.VideoHolder;

public enum FeedType implements IType {
    EMPTY(0, EmptyHolder.class), PHOTO(1, PhotoHolder.class), VIDEO(2, VideoHolder.class);
    //, PHOTOS(3), VIDEOS(4), LINK(5), TEXT(6), LOCATION(7);

    private int value;
    @NonNull
    private Class<? extends AbsFeedHolder> holderClass;

    FeedType(int value,@NonNull Class<? extends AbsFeedHolder> holderClass) {
        this.value = value;
        this.holderClass = holderClass;
    }

    @NonNull
    @Override
    public int getValue() {
        return value;
    }

    @NonNull
    @Override
    public String getName() {
        return name();
    }

    @NonNull
    @Override
    public Class<? extends AbsFeedHolder> getHolderClass() {
        return holderClass;
    }

    public String getValueStr() {
        return String.valueOf(value);
    }

    public static FeedType parse(int value) {
        FeedType obj = EMPTY;
        switch (value) {
            case 1:
                obj = PHOTO;
                break;
            case 2:
                obj = VIDEO;
                break;
        }
        return obj;
    }

    public static FeedType parseByName(String name) {
        FeedType obj = FeedType.EMPTY;
        if (name == null) return obj;
        if (name.equalsIgnoreCase(PHOTO.name())) {
            obj = PHOTO;
        } else if (name.equalsIgnoreCase(VIDEO.name())) {
            obj = VIDEO;
        } else {
            obj = EMPTY;
        }
        return obj;
    }
}

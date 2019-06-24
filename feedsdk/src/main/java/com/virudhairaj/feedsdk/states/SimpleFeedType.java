package com.virudhairaj.feedsdk.states;

import android.support.annotation.NonNull;

import com.virudhairaj.feedcore.AbsFeedHolder;
import com.virudhairaj.feedcore.IType;

/**
 * Generic FeedType
 * for customization, create enum with your own types and your own logic
 */

public enum SimpleFeedType implements IType {

    EMPTY(0), PHOTO(1), VIDEO(2), PHOTOS(3), VIDEOS(4), LINK(5), TEXT(6), LOCATION(7);
    private int value;

    SimpleFeedType(int value) {
        this.value = value;
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
        return AbsFeedHolder.class;
    }

    public String getValueStr() {
        return String.valueOf(value);
    }

    public static SimpleFeedType parse(int value) {
        SimpleFeedType obj = EMPTY;
        switch (value) {
            case 1:
                obj = PHOTO;
                break;
            case 2:
                obj = VIDEO;
                break;
            case 3:
                obj = PHOTOS;
                break;
            case 4:
                obj = VIDEOS;
                break;
            case 5:
                obj = LINK;
                break;
            case 6:
                obj = TEXT;
                break;
            case 7:
                obj = LOCATION;
                break;
        }
        return obj;
    }

    public static SimpleFeedType parseByName(String name) {
        SimpleFeedType obj = SimpleFeedType.EMPTY;
        try{
            obj= SimpleFeedType.valueOf(name);
        }catch (Exception e){
        }
        return obj;
    }
}

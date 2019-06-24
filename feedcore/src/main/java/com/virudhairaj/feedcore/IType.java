package com.virudhairaj.feedcore;

import android.support.annotation.NonNull;

/**
 * Need to implement this interface in custom FeedType enum or class or concrete class and pass that to AbsFeed class
 * suggested way is enum.
 */

public interface IType {

    @NonNull
    public int getValue();

    @NonNull
    public String getName();

    @NonNull
    public Class<? extends AbsFeedHolder> getHolderClass();

}

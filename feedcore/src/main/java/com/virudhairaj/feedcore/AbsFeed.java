package com.virudhairaj.feedcore;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


/***
 * This is class need to extended for custom Feed
 * This accepts enum as param which has implemented IfeedType interface
 */
public class AbsFeed {

    @NonNull
    protected IType feedType; //must specify feed type
    /**
     * for single selection or multi-selection of feed
     */
    protected boolean selected;

    /***
     * This constructor accepts nullable object. If its null then its assign default as feed type
     * @param feedType
     */
    public AbsFeed(@Nullable IType feedType) {
        this.feedType = feedType!=null?feedType:new IType() {
            @NonNull
            @Override
            public int getValue() {
                return 0;
            }

            @NonNull
            @Override
            public String getName() {
                return "default";
            }

            @NonNull
            @Override
            public Class<? extends AbsFeedHolder> getHolderClass() {
                return AbsFeedHolder.class;
            }
        };
    }

    @NonNull
    public IType getFeedType() {
        return feedType;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }
}

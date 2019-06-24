package com.virudhairaj.feedcore;


import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * if you want to add custom listeners onBindViewHolder, you can use member variables and getter setters
 * @param <Feed>
 */
public abstract class AbsFeedHolder<Feed extends AbsFeed> extends RecyclerView.ViewHolder {


    /***
     * this function used to send custom context to avoid inflate exception
     * @param context
     * @param layoutResId
     * @param parent
     * @param attachToRoot
     */
    public AbsFeedHolder(@NonNull Context context, @LayoutRes int layoutResId, @NonNull ViewGroup parent, boolean attachToRoot) {
        super(LayoutInflater.from(context).inflate(layoutResId, parent, attachToRoot));
    }



    /***
     * This function automatically invoked from onBindViewHolder in AbsFeedAdapter or its child class. and must call super.bind
     * By default it can handle itemView click listener. Can customize this function by override it in your own class.
     * @param feed
     * @param position
     * @param extras
     */
    public void onItemBind(@NonNull final Feed feed, final int position, @Nullable final Object... extras) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(feed, position, extras);
            }
        });
    }

    /***
     * This function automatically invoked when item click.
     * @param feed
     * @param position
     * @param extras
     */
    protected void onItemClick(Feed feed, int position, Object... extras) {

    }

    /***
     * Returns visible percent of given view.
     * < 0  -> view not visible
     * > 0  -> view visible
     * @param view
     * @return
     */
    public static int getVisiblePercent(View view) {
        if (view.isShown()) {
            int percent = 0;
            try {
                Rect mCurr1entViewRect = new Rect();
                view.getLocalVisibleRect(mCurr1entViewRect);
                int height = view.getHeight();
                boolean isViewPartiallyHiddenBottom = mCurr1entViewRect.bottom > 0 && mCurr1entViewRect.bottom < height;
                boolean isViewPartiallyHiddenTop = mCurr1entViewRect.top > 0;
                if (isViewPartiallyHiddenTop) {
                    // view is partially hidden behind the top edge
                    percent = (height - mCurr1entViewRect.top) * 100 / height;
                } else if (isViewPartiallyHiddenBottom) {
                    percent = mCurr1entViewRect.bottom * 100 / height;
                }

            } catch (Exception e) {
                return percent;
            }
            return percent;
        } else {
            return -1;
        }
    }

}

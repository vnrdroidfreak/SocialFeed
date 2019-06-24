package com.virudhairaj.feedsdk;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.GridLayoutAnimationController;

import com.virudhairaj.feedcore.AbsFeedAdapter;
import com.virudhairaj.feedsdk.abstracts.OnLoadMoreListener;

public class FeedRecycler extends RecyclerView {

    private View mEmptyView;
    private View loadMoreView;
    private int emptyViewResId = 0;
    private int loadMoreViewResId = 0;
    private int loadMoreThreshold = 5; // The minimum amount of items to have below your current scroll position before loading more.

    private OnLoadMoreListener onLoadMoreListener = null;

    final AdapterDataObserver observer = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            initEmptyView();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
            initEmptyView();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);
            initEmptyView();
        }
    };


    private EndlessOnScrollListener endlessOnScrollListener = new EndlessOnScrollListener() {
        @Override
        public void onLoadMore(int currentPage) {
            if (onLoadMoreListener != null) {
                onLoadMoreListener.onLoadMoreFeed(currentPage);
                setLoadMoreViewVisible(true);
                onLoadMoreListener.onLoadMoreStateChanged(true);
            }

        }
    };

    public FeedRecycler(Context context) {
        super(context);
        init(null);
    }

    public FeedRecycler(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public FeedRecycler(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }


    private void init(AttributeSet attrs) {
        if (attrs != null) {
            final TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.FeedRecycler);
            if (array != null) {
                emptyViewResId = array.getResourceId(R.styleable.FeedRecycler_emptyView, 0);
                loadMoreViewResId = array.getResourceId(R.styleable.FeedRecycler_loadMoreView, 0);
                loadMoreThreshold = array.getResourceId(R.styleable.FeedRecycler_loadMoreThreshold, 5);
                array.recycle();
            }
        }
        if (loadMoreThreshold > 0) endlessOnScrollListener.setLoadMoreThreshold(loadMoreThreshold);
        addOnScrollListener(endlessOnScrollListener);
    }

    private void initEmptyView() {
        if (mEmptyView != null) {
            setEmptyViewVisibility(isDataEmpty());
        }
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void setEmptyView(View view) {
        Log.d(this.getClass().getSimpleName(), "set empty View  " + view);
        this.mEmptyView = view;
        initEmptyView();
    }

    public void setLoadMoreView(View view) {
        Log.d(this.getClass().getSimpleName(), "set load more View  " + view);
        this.loadMoreView = view;
        setLoadMoreViewVisible(false);
//        initEmptyView();
    }


    public void setLoadMoreThreshold(int loadMoreThreshold) {
        if (loadMoreThreshold > 0) {
            this.loadMoreThreshold = loadMoreThreshold;
            endlessOnScrollListener.setLoadMoreThreshold(loadMoreThreshold);
        }
    }

    @Deprecated
    @Override
    public void setAdapter(Adapter adapter) {
        Log.e(getClass().getSimpleName(), "setAdapter(Adapter adapter) not work in this class. please use setAdapter(AbsFeedAdapter adapter)");
        // set adapter not work in this class please use
    }

    void setAdapter(AbsFeedAdapter adapter) {
        Adapter oldAdapter = getAdapter();
        super.setAdapter(adapter);
        if (oldAdapter != null) {
            oldAdapter.unregisterAdapterDataObserver(observer);
        }
        if (adapter != null) {
            adapter.registerAdapterDataObserver(observer);
        }
    }


    @Override
    protected void attachLayoutAnimationParameters(View child, ViewGroup.LayoutParams params,
                                                   int index, int count) {
        final LayoutManager layoutManager = getLayoutManager();
        if (getAdapter() != null && layoutManager instanceof GridLayoutManager) {

            GridLayoutAnimationController.AnimationParameters animationParams =
                    (GridLayoutAnimationController.AnimationParameters) params.layoutAnimationParameters;

            if (animationParams == null) {
                // If there are no animation parameters, create new once and attach them to
                // the LayoutParams.
                animationParams = new GridLayoutAnimationController.AnimationParameters();
                params.layoutAnimationParameters = animationParams;
            }

            // Next we are updating the parameters

            // Set the number of items in the RecyclerView and the index of this item
            animationParams.count = count;
            animationParams.index = index;

            // Calculate the number of columns and rows in the grid
            final int columns = ((GridLayoutManager) layoutManager).getSpanCount();
            animationParams.columnsCount = columns;
            animationParams.rowsCount = count / columns;

            // Calculate the column/row position in the grid
            final int invertedIndex = count - 1 - index;
            animationParams.column = columns - 1 - (invertedIndex % columns);
            animationParams.row = animationParams.rowsCount - 1 - invertedIndex / columns;

        } else {
            // Proceed as normal if using another type of LayoutManager
            super.attachLayoutAnimationParameters(child, params, index, count);
        }
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        try {
            if (emptyViewResId != 0) {
                setEmptyView(((View) getParent()).findViewById(emptyViewResId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (loadMoreViewResId != 0) {
                setLoadMoreView(((View) getParent()).findViewById(loadMoreViewResId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setEmptyViewVisibility(boolean isVisible) {
        if (mEmptyView != null) {
            mEmptyView.setVisibility(isVisible ? VISIBLE : GONE);
        }
    }

    public void setLoadMoreViewVisible(boolean isVisible) {
        if (loadMoreView != null) {
            loadMoreView.setVisibility(isVisible ? VISIBLE : GONE);
        }
    }

    public void setLoadMoreCompleted() {
        setLoadMoreViewVisible(false);
        if (onLoadMoreListener != null) onLoadMoreListener.onLoadMoreStateChanged(false);
    }

    public void setVisibility(boolean isVisible) {
        setVisibility(isVisible ? VISIBLE : GONE);
    }

    public boolean isDataEmpty() {
        return getAdapter() == null || getAdapter().getItemCount() == 0;
    }


}

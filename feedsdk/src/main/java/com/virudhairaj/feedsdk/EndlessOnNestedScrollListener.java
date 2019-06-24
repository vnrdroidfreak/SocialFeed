package com.virudhairaj.feedsdk;


import android.support.annotation.NonNull;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;


public abstract class EndlessOnNestedScrollListener implements NestedScrollView.OnScrollChangeListener {

    private int previousTotal = 0; // The total number of items in the dataset after the last load
    private boolean loading = true; // True if we are still waiting for the last set of data to load.
    private int visibleThreshold = 5; // The minimum amount of items to have below your current scroll position before loading more.
    private int firstVisibleItem, visibleItemCount, totalItemCount;

    private int currentPage = 1;

    private final RecyclerView recyclerView;




    public EndlessOnNestedScrollListener(@NonNull RecyclerView recyclerView) {
        this.recyclerView=recyclerView;
        reset();
    }

    public EndlessOnNestedScrollListener(@NonNull RecyclerView recyclerView, int loadMoreThreshold) {
        this.recyclerView=recyclerView;
        reset();
        this.visibleThreshold = loadMoreThreshold;
    }

    public EndlessOnNestedScrollListener setLoadMoreThreshold(int loadMoreThreshold) {
        if (loadMoreThreshold > 0)
            this.visibleThreshold = loadMoreThreshold;
        return this;
    }


    public int getCurrentPage() {
        return currentPage;
    }

    public void reset() {
        currentPage = 1;
        firstVisibleItem = 0;
        visibleItemCount = 0;
        totalItemCount = 0;
        loading = true;
        previousTotal = 0;
    }



    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//        if (v.getChildAt(v.getChildCount() - 1) != null) {
//            if ((scrollY >= (v.getChildAt(v.getChildCount() - 1).getMeasuredHeight() - v.getMeasuredHeight())) &&
//                    scrollY > oldScrollY) {
//                //code to fetch more data for endless scrolling
//                currentPage = currentPage + 1;
//               onLoadMore(currentPage);
//            }
//        }

        try {
            visibleItemCount = recyclerView.getChildCount();
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager == null) {
                return;
            }
            totalItemCount = layoutManager.getItemCount();
            if (layoutManager instanceof GridLayoutManager) {
                firstVisibleItem = ((GridLayoutManager) layoutManager).findFirstVisibleItemPosition();
            } else if (layoutManager instanceof LinearLayoutManager) {
                firstVisibleItem = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                int[] positions = ((StaggeredGridLayoutManager) layoutManager).findFirstVisibleItemPositions(null);
                firstVisibleItem = positions.length > 0 ? positions[0] : -1;
            }
            if (loading) {
                if (totalItemCount > previousTotal) {
                    loading = false;
                    previousTotal = totalItemCount;
                }
            }
            if (visibleThreshold > 0 && !loading && (totalItemCount - visibleItemCount)
                    <= (firstVisibleItem + visibleThreshold)) {
                currentPage++;
                onLoadMore(currentPage);
                loading = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public abstract void onLoadMore(int currentPage);
}
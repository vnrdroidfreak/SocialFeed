package com.virudhairaj.feedsdk;


import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.AbsListView;

import com.virudhairaj.feedcore.AbsFeedHolder;
import com.virudhairaj.feedsdk.states.ScrollDirection;


public abstract class FeedOnScrollListener extends RecyclerView.OnScrollListener {

    private RecyclerView recycler;
    private int currentFeedPosition = -1;

    public FeedOnScrollListener(@NonNull RecyclerView recycler) {
        this.recycler = recycler;
    }

    AbsFeedHolder currentFocusedLayout = null;

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
//            recyclerView.getLayoutManager()

//            final int positionView = ((LinearLayoutManager) newfeedsrecycle.getLayoutManager()).findFirstVisibleItemPosition();
//            if (positionView < 0 || positionView >= myNewsFeedsArrayList.size())
//                return;
//            final Feed feed = myNewsFeedsArrayList.get(positionView);
//            if (feed.isVideoFeed()) {
//                currentFocusedLayout = new FeedHolder((newfeedsrecycle.getLayoutManager()).findViewByPosition(positionView));
//                feedAdapter.setCurrentVideoViewObj(currentFocusedLayout.videoView);
//                int visiblePercent = App.getVisiblePercent(currentFocusedLayout.videoView);
//                if (visiblePercent > 30) {
//                    currentFocusedLayout.playVideo();
//                }
//            }
        }
    }


    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if (dy > 0 || dx > 0) {
            //scrolled up or left
            int firstPosition = getFirstVisibleItemPosition();
            if (recycler.getLayoutManager() instanceof LinearLayoutManager) {
                firstPosition = ((LinearLayoutManager) recycler.getLayoutManager()).findFirstVisibleItemPosition();
            } else if (recycler.getLayoutManager() instanceof GridLayoutManager) {
                firstPosition = ((GridLayoutManager) recycler.getLayoutManager()).findFirstVisibleItemPosition();
            } else if (recycler.getLayoutManager() instanceof StaggeredGridLayoutManager) {
                int[] positions = ((StaggeredGridLayoutManager) recycler.getLayoutManager()).findFirstVisibleItemPositions(null);
                firstPosition = positions.length > 0 ? positions[0] : -1;
            }
            if (firstPosition >= 0) {
                onFeedScrolling(firstPosition, dy > 0 ? ScrollDirection.up : ScrollDirection.left);
                if (currentFeedPosition != firstPosition) {
                    onFeedScrolled(currentFeedPosition,dy > 0 ? ScrollDirection.up : ScrollDirection.left);
                }
                currentFeedPosition = firstPosition;
            }

        } else if (dy < 0 || dx < 0) {
            //scrolled down or right
            int lastPosition = getLastVisibleItemPosition();
            if (lastPosition == -1) {
                if (recycler.getLayoutManager() instanceof LinearLayoutManager) {
                    lastPosition = ((LinearLayoutManager) recycler.getLayoutManager()).findFirstVisibleItemPosition();
                } else if (recycler.getLayoutManager() instanceof GridLayoutManager) {
                    lastPosition = ((GridLayoutManager) recycler.getLayoutManager()).findFirstVisibleItemPosition();
                } else if (recycler.getLayoutManager() instanceof StaggeredGridLayoutManager) {
                    int[] positions = ((StaggeredGridLayoutManager) recycler.getLayoutManager()).findFirstVisibleItemPositions(null);
                    lastPosition = positions.length > 0 ? positions[0] : -1;
                }
            }

            if (lastPosition >= 0) {
                onFeedScrolling(lastPosition, dy < 0 ? ScrollDirection.down : ScrollDirection.right);
                if (currentFeedPosition != lastPosition) {
                    onFeedScrolled(currentFeedPosition,dy < 0 ? ScrollDirection.down : ScrollDirection.right);
                }
                currentFeedPosition = lastPosition;
            }
        }

//        LinearLayoutManager layoutManager = ((LinearLayoutManager) newfeedsrecycle.getLayoutManager());
//        try {
//            if (dy > 0) {
//                //scroll up get first visible item
//                int position = layoutManager.findFirstVisibleItemPosition();
//                if (position >= 0 && position < myNewsFeedsArrayList.size()) {
//                    final Feed feed = myNewsFeedsArrayList.get(position);
//                    if (feed.isVideoFeed()) {
//                        final FeedHolder holder = new FeedHolder(layoutManager.findViewByPosition(position));
//                        feedAdapter.setCurrentVideoViewObj(holder.videoView);
//                        int curVisiblePercent = App.getVisiblePercent(holder.videoView);
//                        if (curVisiblePercent > 30 && !holder.videoView.isPlaying()) {
//                            holder.videoView.start();
//                            if (!holder.videoView.isPlaying()) {
//                                holder.videoView.start();
////                                        Log.e(TAG, "video started");
//                            } else {
////                                        Log.e(TAG, "video already started");
//                            }
//                        } else if (curVisiblePercent <= 30 && holder.videoView.isPlaying()) {
//                            holder.videoView.pause();
////                                    Log.e(TAG, "video paused");
//                        }
////                                Log.e(TAG, "Scroll up  current (" + position + ") visiblePercent: " + curVisiblePercent);
//                    }
//                }
//
//            } else {
//                //scroll down get last visible item
//                int position = layoutManager.findLastVisibleItemPosition();
//                final Feed feed = myNewsFeedsArrayList.get(position);
//                if (feed.isVideoFeed()) {
//                    final FeedHolder holder = new FeedHolder(layoutManager.findViewByPosition(position));
//                    int curVisiblePercent = App.getVisiblePercent(holder.videoView);
//                    if (curVisiblePercent < 30 && holder.videoView.isPlaying()) {
//                        holder.videoView.pause();
//                    }
////                            Log.e(TAG, "Scroll down  current (" + position + ") visiblePercent: " + curVisiblePercent);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public int getFirstVisibleItemPosition() {
        return -1;
    }

    public int getLastVisibleItemPosition() {
        return -1;
    }

    public abstract void onFeedScrolling(int position, ScrollDirection direction);

    public abstract void onFeedScrolled(int position,ScrollDirection direction);

}
package com.virudhairaj.feedsdk.abstracts;



public interface OnLoadMoreListener {

    public void onLoadMoreFeed(int pageNum);

    public void onLoadMoreStateChanged(boolean isStarted);
}

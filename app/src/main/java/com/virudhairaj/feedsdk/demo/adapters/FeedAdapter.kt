package com.virudhairaj.feedsdk.demo.adapters

import com.virudhairaj.feedcore.AbsFeed
import com.virudhairaj.feedcore.AbsFeedAdapter
import com.virudhairaj.feedcore.AbsFeedHolder
import com.virudhairaj.feedsdk.demo.model.Feed
import java.util.*


class FeedAdapter : AbsFeedAdapter<AbsFeedHolder<AbsFeed>> {

    companion object {
        private val TAG = "FeedAdapter"
    }




    constructor() {

    }

    constructor(data: ArrayList<Feed>) {
        feeds=data
    }







}
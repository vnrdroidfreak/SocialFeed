package com.virudhairaj.feedsdk.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager

import com.virudhairaj.feedsdk.demo.adapters.FeedAdapter
import com.virudhairaj.feedsdk.demo.model.Feed
import com.virudhairaj.feedsdk.demo.model.VideoFeed
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var views: Views?=null
    val feeds:ArrayList<Feed> = arrayListOf();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        feeds.addAll(VideoFeed.getSampleData());

        views=Views()
        views?.init()
        views?.uiLogic()
    }


    inner class Views {
        val layoutManager:LinearLayoutManager
        val feedAdapter: FeedAdapter
        constructor(){
            layoutManager=LinearLayoutManager(this@MainActivity)
            feedAdapter= FeedAdapter(feeds)
        }
        fun init(){
            recycler.layoutManager=layoutManager
            recycler.adapter=feedAdapter
        }
        fun uiLogic(){


        }

    }
}

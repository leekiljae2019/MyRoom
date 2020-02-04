package com.afterwork.myroom.view

import android.os.Bundle
import com.afterwork.myroom.R
import com.afterwork.myroom.databinding.ActivityPagingnetworkroomBinding
import com.afterwork.myroom.view.common.BaseActivity

class PagingNetworkRoomActivity: BaseActivity<ActivityPagingnetworkroomBinding>(){
    companion object{
        val TAG = "PagingNetworkRoomActivity"
    }

    override val layoutResourceId: Int
        get() = R.layout.activity_pagingnetworkroom


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}
package com.afterwork.myroom.view

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.afterwork.myroom.R
import com.afterwork.myroom.data.room.BasicRoomDB
import com.afterwork.myroom.databinding.ActivityPagingroomBinding
import com.afterwork.myroom.view.common.BaseActivity
import com.afterwork.myroom.viewmodel.pagingroom.PagingRoomAdapter
import kotlinx.android.synthetic.main.activity_pagingroom.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class PagingRoomActivity: BaseActivity<ActivityPagingroomBinding>(){
    companion object{
        var TAG = "PagingRoomActivity"
    }

    override val layoutResourceId: Int
        get() = R.layout.activity_pagingroom

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.vmPR = getViewModel{ parametersOf(BasicRoomDB.getInstance(this)?.basicRoomDao()) }
        binding.lifecycleOwner = this

        val adapter = PagingRoomAdapter()
        list.adapter = adapter

        binding.vmPR?.load(0)?.observe(this, Observer{
            Log.d(TAG, "CALLED binding.vmPR.load().observe()")
            adapter.submitList(it)
        })
    }
}

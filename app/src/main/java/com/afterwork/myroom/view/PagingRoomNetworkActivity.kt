package com.afterwork.myroom.view

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.afterwork.myroom.R
import com.afterwork.myroom.data.room.BasicRoomDB
import com.afterwork.myroom.databinding.ActivityPagingroomnetworkBinding
import com.afterwork.myroom.view.common.BaseActivity
import com.afterwork.myroom.viewmodel.common.PagingRoomAdapter
import kotlinx.android.synthetic.main.activity_pagingroomnetwork.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class PagingRoomNetworkActivity: BaseActivity<ActivityPagingroomnetworkBinding>(){
    companion object{
        val TAG = "PagingRNActivity"
    }

    override val layoutResourceId: Int
        get() = R.layout.activity_pagingroomnetwork


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.vmPRN = getViewModel()
        binding.lifecycleOwner = this

        val adapter = PagingRoomAdapter()
        list.adapter = adapter

        binding.vmPRN?.load(0)?.observe(this, Observer{
            Log.d(TAG, "CALLED binding.vmPR.load().observe()")
            adapter.submitList(it)
        })
    }
}
package com.afterwork.myroom.viewmodel.pagingroomnetwork

import android.util.Log
import androidx.paging.PagedList
import com.afterwork.myroom.data.room.BasicRoomDao
import com.afterwork.myroom.data.room.BasicRoomEntity
import com.afterwork.myroom.data.room.BasicRoomTaskBuilder
import com.afterwork.myroom.viewmodel.common.NotNullMutableLiveData

class MyBoundaryCallback(val dao: BasicRoomDao, val refreshing: NotNullMutableLiveData<Boolean>): PagedList.BoundaryCallback<BasicRoomEntity>(){

    companion object{
        val TAG = "MyBoundaryCallback"
    }

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        Log.d(TAG, "onZeroItemsLoaded()")
        var items = BasicRoomTaskBuilder.buildFakeNetwork(0, refreshing).execute().get()
        BasicRoomTaskBuilder.buildBulkInsert(items, dao, refreshing).execute()
    }

    override fun onItemAtFrontLoaded(itemAtFront: BasicRoomEntity) {
        super.onItemAtFrontLoaded(itemAtFront)
        Log.d(TAG, "onItemAtFrontLoaded()")
    }

    override fun onItemAtEndLoaded(itemAtEnd: BasicRoomEntity) {
        super.onItemAtEndLoaded(itemAtEnd)
        Log.d(TAG, "onItemAtEndLoaded()")
        var items = BasicRoomTaskBuilder.buildFakeNetwork(itemAtEnd.id, refreshing).execute().get()
        BasicRoomTaskBuilder.buildBulkInsert(items, dao, refreshing).execute()
    }
}
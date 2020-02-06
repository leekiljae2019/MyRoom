package com.afterwork.myroom.viewmodel.pagingroomnetwork

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.afterwork.myroom.data.room.BasicRoomDao
import com.afterwork.myroom.data.room.BasicRoomEntity
import com.afterwork.myroom.viewmodel.common.NotNullMutableLiveData

class PagingRoomNetworkViewModel(val dao: BasicRoomDao): ViewModel(){
    companion object{
        val TAG = "PagingRNViewModel"
    }

    val _refreshing: NotNullMutableLiveData<Boolean> = NotNullMutableLiveData(false)
    val refreshing: LiveData<Boolean> get() = _refreshing

    var pagedListBuilder: LivePagedListBuilder<Int, BasicRoomEntity>
    var items: LiveData<PagedList<BasicRoomEntity>>? = null


    init{
        Log.d(TAG, "init")

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(20)
            .setPrefetchDistance(3)
            .setPageSize(20)
            .build()

        pagedListBuilder = LivePagedListBuilder<Int, BasicRoomEntity>(
            dao.getPagingItems(),
            config
        ).setBoundaryCallback(MyBoundaryCallback(dao, _refreshing))
    }

    fun load(key: Int): LiveData<PagedList<BasicRoomEntity>> {
        items = pagedListBuilder.setInitialLoadKey(key).build()
        _refreshing.value = false
        return items!!
    }

    fun onRefreshing(){
        _refreshing.value = false
    }
}

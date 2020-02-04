package com.afterwork.myroom.viewmodel.pagingroom

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.afterwork.myroom.data.room.BasicRoomDao
import com.afterwork.myroom.data.room.BasicRoomEntity
import com.afterwork.myroom.viewmodel.common.NotNullMutableLiveData

class PagingRoomViewModel(val dao: BasicRoomDao): ViewModel(){
    companion object{
        val TAG = "PagingRoomViewModel"
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
        )

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
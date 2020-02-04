package com.afterwork.myroom.viewmodel.pagingnetworkroom

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.afterwork.myroom.data.room.BasicRoomDao
import com.afterwork.myroom.viewmodel.common.NotNullMutableLiveData

class PagingNetworkRoomViewModel(val dao: BasicRoomDao): ViewModel(){
    companion object{
        val TAG = "PagingNetworkRoomViewModel"
    }

    val _refreshing: NotNullMutableLiveData<Boolean> = NotNullMutableLiveData(false)
    val refreshing: LiveData<Boolean> get() = _refreshing


}
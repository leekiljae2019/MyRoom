package com.afterwork.myroom.viewmodel.basicroom

import android.os.AsyncTask
import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.afterwork.myroom.data.room.BasicRoomDao
import com.afterwork.myroom.data.room.BasicRoomEntity
import com.afterwork.myroom.data.room.BasicRoomTaskBuilder
import com.afterwork.myroom.viewmodel.common.NotNullMutableLiveData


class BasicRoomViewModel(val dao: BasicRoomDao): ViewModel(){
    companion object{
        val TAG = "BasicRoomViewModel"
    }

    private val _items: NotNullMutableLiveData<List<BasicRoomEntity>> =
        NotNullMutableLiveData(mutableListOf())
    val items: LiveData<List<BasicRoomEntity>> get() = _items

    var currentCount = ObservableInt(0)

    init {
        Thread(Runnable {
            currentCount.set(dao.getLastId())
            Log.d(TAG, "init(lastId: ${currentCount})")
            _items.postValue(dao.getAll())
        }).start()
    }

    fun insert(){
        currentCount.set(currentCount.get()+1)
        BasicRoomTaskBuilder.buildInsert(dao, _items).execute()
    }
}


@BindingAdapter("itemCount")
fun setDataInfo(view: TextView, count: Int){
    if(count == 0){
        view.setText("ItemCount: Empty!!!")
        return
    }
    view.setText("ItemCount: $count")
}

@BindingAdapter("items")
fun setAdapter(view: RecyclerView, items: List<BasicRoomEntity>){
    view.adapter?.run {
        if (this is BasicRoomListAdapter) {
            Log.d("setAdapter", "item size: ${items.size}")
            this.items = items
            this.notifyDataSetChanged()
        }
    } ?: run {
        Log.d("setAdapter", "BasicRoomListAdapter create")
        BasicRoomListAdapter(items)
            .apply { view.adapter = this }
    }
}

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
import com.afterwork.myroom.viewmodel.common.NotNullMutableLiveData


class BasicRoomViewModel(val dao: BasicRoomDao): ViewModel(){
    companion object{
        val TAG = "BasicRoomViewModel"

        val D_TITLE = "title"
        val D_DESC = "description"
        val D_LINK = "https://www.naver.com/"
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
        var index = currentCount.get()+1
        currentCount.set(index)
        InsertTask(dao, _items, index).execute()
    }

    class InsertTask(val dao: BasicRoomDao, val items: MutableLiveData<List<BasicRoomEntity>>, val index: Int) : AsyncTask<Void, Void, BasicRoomEntity>(){
        override fun doInBackground(vararg p0: Void?): BasicRoomEntity {
            val item = BasicRoomEntity(
                index,
                "$D_TITLE${index}",
                "$D_DESC${index}",
                "$D_LINK${index}"
            )
            dao.insert(item)
            Log.d(TAG, "insert(count: ${dao.getCount()})")
            return item
        }

        override fun onPostExecute(result: BasicRoomEntity) {
            super.onPostExecute(result)
            this.items.postValue(this.items.value?.plus(result))
        }
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

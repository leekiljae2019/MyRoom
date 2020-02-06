package com.afterwork.myroom.data.room

import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.afterwork.myroom.viewmodel.common.NotNullMutableLiveData


object BasicRoomTaskBuilder{
    fun buildInsert(dao: BasicRoomDao, items: MutableLiveData<List<BasicRoomEntity>>): InsertTask{
        return InsertTask(dao, items)
    }

    fun buildBulkInsert(items: List<BasicRoomEntity>, dao: BasicRoomDao, refreshing: NotNullMutableLiveData<Boolean>): BulkInsertTask{
        return BulkInsertTask(items, dao, refreshing)
    }

    fun buildFakeNetwork(index: Int, refreshing: NotNullMutableLiveData<Boolean>): FakeNetworkTask{
        return FakeNetworkTask(index, refreshing)
    }

}


class InsertTask(val dao: BasicRoomDao, val items: MutableLiveData<List<BasicRoomEntity>>) : AsyncTask<Void, Void, BasicRoomEntity>(){
    override fun doInBackground(vararg p0: Void?): BasicRoomEntity {
        var index = dao.getLastId()+1
        val item = BasicRoomEntity(
            index,
            "title${index}",
            "description${index}",
            "https://www.naver.com/${index}"
        )
        dao.insert(item)
        Log.d("InsertTask", "insert(count: ${dao.getCount()})")
        return item
    }

    override fun onPostExecute(result: BasicRoomEntity) {
        super.onPostExecute(result)
        this.items.postValue(this.items.value?.plus(result))
    }
}

class BulkInsertTask(val items: List<BasicRoomEntity>, val dao: BasicRoomDao, val refreshing: NotNullMutableLiveData<Boolean>) : AsyncTask<Void, Void, Int>(){
    override fun doInBackground(vararg p0: Void?): Int {
        dao.bulkInsert(items)
        Log.d("BulkInsertTask", "bulkInsert(count: ${dao.getCount()})")
        return items.size
    }

    override fun onPostExecute(result: Int?) {
        super.onPostExecute(result)

        refreshing.value = false
    }
}

class FakeNetworkTask(val index: Int, val refreshing: NotNullMutableLiveData<Boolean>) : AsyncTask<Void, Void, List<BasicRoomEntity>>(){
    override fun doInBackground(vararg p0: Void?): List<BasicRoomEntity> {
        var items = ArrayList<BasicRoomEntity>()
        for(i in (index+1)..(index+20)) {
            items.add(BasicRoomEntity(
                i,
                "title${i}",
                "description${i}",
                "https://www.naver.com/${i}"
            ))
        }

        Log.d("FakeNetworkTask", "bulkInsert(index: ${index})")
        return items
    }

    override fun onPreExecute() {
        super.onPreExecute()

        refreshing.value = true
    }

}
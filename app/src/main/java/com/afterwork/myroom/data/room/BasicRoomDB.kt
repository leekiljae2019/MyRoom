package com.afterwork.myroom.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(BasicRoomEntity::class), version = 1)
abstract class BasicRoomDB: RoomDatabase(){
    companion object{
        private var INSTANCE: BasicRoomDB? = null

        fun getInstance(appContext: Context): BasicRoomDB?{
            if(INSTANCE == null){
                synchronized(BasicRoomDB::class) {
                    INSTANCE = Room.databaseBuilder(
                        appContext,
                        BasicRoomDB::class.java, "basicroom.db"
                    ).build()
                }
            }

            return INSTANCE
        }

        fun destoryInstance(){
            if(INSTANCE != null) {
                synchronized(BasicRoomDB::class) {
                    INSTANCE?.close()
                    INSTANCE = null
                }
            }
        }
    }

    abstract fun basicRoomDao(): BasicRoomDao
}

package com.afterwork.myroom.data.room

import androidx.paging.DataSource
import androidx.room.*

@Dao
interface BasicRoomDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(item: BasicRoomEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun bulkInsert(items: List<BasicRoomEntity>)

    @Delete
    fun delete(item: BasicRoomEntity)

    @Query("SELECT * FROM tb_br ORDER BY id ASC")
    fun getAll(): List<BasicRoomEntity>

    @Query("SELECT COUNT(id) FROM tb_br")
    fun getCount(): Int

    @Query("SELECT MAX(id) FROM tb_br")
    fun getLastId(): Int

    @Query("SELECT * FROM tb_br ORDER BY id ASC")
    fun getPagingItems(): DataSource.Factory<Int, BasicRoomEntity>

}
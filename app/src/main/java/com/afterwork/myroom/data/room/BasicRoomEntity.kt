package com.afterwork.myroom.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

//@Entity(tableName = "tb_br", indices = arrayOf(Index(value = ["id"])))
@Entity(tableName = "tb_br")
data class BasicRoomEntity(
    @PrimaryKey @ColumnInfo(name= "id") val id: Int,
    @ColumnInfo(name= "title") val title: String,
    @ColumnInfo(name= "desc") val desc: String,
    @ColumnInfo(name= "link_url") val linkUrl: String
)
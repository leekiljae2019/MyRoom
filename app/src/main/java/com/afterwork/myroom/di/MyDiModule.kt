package com.afterwork.myroom.di

import androidx.room.Room
import com.afterwork.myroom.data.room.BasicRoomDB
import com.afterwork.myroom.data.room.BasicRoomDao
import com.afterwork.myroom.viewmodel.basicroom.BasicRoomViewModel
import com.afterwork.myroom.viewmodel.pagingroom.PagingRoomViewModel
import com.afterwork.myroom.viewmodel.pagingroomnetwork.PagingRoomNetworkViewModel
import io.reactivex.Single
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import kotlin.coroutines.coroutineContext

var viewModelPart = module {
    viewModel {
        BasicRoomViewModel(get())
    }

    viewModel {
        PagingRoomViewModel(get())
    }

    viewModel {
        PagingRoomNetworkViewModel(get())
    }
}

var daoPart = module {
    single<BasicRoomDao>{
        BasicRoomDB.getInstance(androidContext())!!.basicRoomDao()
//        Room.databaseBuilder(
//            androidContext(),
//            BasicRoomDB::class.java,
//        "basicroom.db"
//        ).build().basicRoomDao()
    }
}

var myDiModule = listOf(
    daoPart,
    viewModelPart
)

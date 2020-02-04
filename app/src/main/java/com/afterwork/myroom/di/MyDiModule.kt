package com.afterwork.myroom.di

import com.afterwork.myroom.data.room.BasicRoomDao
import com.afterwork.myroom.viewmodel.basicroom.BasicRoomViewModel
import com.afterwork.myroom.viewmodel.pagingroom.PagingRoomViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var viewModelPart = module {
    viewModel {(dao: BasicRoomDao) ->
        BasicRoomViewModel(dao)
    }

    viewModel {(dao: BasicRoomDao) ->
        PagingRoomViewModel(dao)
    }
}

var myDiModule = listOf(
    viewModelPart
)

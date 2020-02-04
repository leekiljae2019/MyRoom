package com.afterwork.myroom.view

import android.os.Bundle
import androidx.lifecycle.Observer
import com.afterwork.myroom.R
import com.afterwork.myroom.data.room.BasicRoomDB
import com.afterwork.myroom.databinding.ActivityBasicroomBinding
import com.afterwork.myroom.view.common.BaseActivity
import kotlinx.android.synthetic.main.activity_basicroom.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class BasicRoomActivity: BaseActivity<ActivityBasicroomBinding>(){

    companion object{
        var TAG = "BasicRoomActivity"
    }

    override val layoutResourceId: Int
        get() = R.layout.activity_basicroom

    var db: BasicRoomDB? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.vmBasic = getViewModel{parametersOf(BasicRoomDB.getInstance(this)?.basicRoomDao())}
        binding.lifecycleOwner = this

        binding.vmBasic?.items?.observe(this, Observer {
            if(!it.isEmpty()) {
                list.scrollToPosition(it.size - 1)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()

        db?.close()
    }
}

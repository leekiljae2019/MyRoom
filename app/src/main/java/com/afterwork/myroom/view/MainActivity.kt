package com.afterwork.myroom.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.afterwork.myroom.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_basic_room.setOnClickListener {
            startActivity(Intent(this, BasicRoomActivity::class.java))
        }

        btn_paging_room.setOnClickListener {
            startActivity(Intent(this, PagingRoomActivity::class.java))
        }

        btn_paging_network_room.setOnClickListener {
            startActivity(Intent(this, PagingRoomNetworkActivity::class.java))
        }
    }
}

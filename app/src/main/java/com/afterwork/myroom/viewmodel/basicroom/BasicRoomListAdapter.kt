package com.afterwork.myroom.viewmodel.basicroom

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afterwork.myroom.R
import com.afterwork.myroom.data.room.BasicRoomEntity
import com.afterwork.myroom.databinding.ItemBasicroomBinding
import com.afterwork.myroom.viewmodel.common.BindingViewHolder


class BasicRoomListAdapter(var items: List<BasicRoomEntity> = arrayListOf())
    : RecyclerView.Adapter<BasicRoomListAdapter.BasicRoomListViewHolder>() {

    companion object{
        val TAG = "BasicRoomListAdapter"
    }

    class BasicRoomListViewHolder(view: View) : BindingViewHolder<ItemBasicroomBinding>(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasicRoomListViewHolder {
        Log.d(TAG, "onCreateViewHolder(viewType: $viewType)")
        return BasicRoomListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_basicroom,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BasicRoomListViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder(position: $position)")
        holder.binding.item = items[position]
    }

}
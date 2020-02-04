package com.afterwork.myroom.viewmodel.pagingroom

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.afterwork.myroom.R
import com.afterwork.myroom.data.room.BasicRoomEntity
import com.afterwork.myroom.databinding.ItemBasicroomBinding
import com.afterwork.myroom.viewmodel.common.BindingViewHolder

class PagingRoomAdapter: PagedListAdapter<BasicRoomEntity, PagingRoomAdapter.MyViewHolder>(
    DIFF_CALLBACK
){

    companion object{
        val TAG = "MyPagingAdapter"

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BasicRoomEntity>() {
            // The ID property identifies when items are the same.
            override fun areItemsTheSame(oldItem: BasicRoomEntity, newItem: BasicRoomEntity) =
                oldItem.id == newItem.id

            // If you use the "==" operator, make sure that the object implements
            // .equals(). Alternatively, write custom data comparison logic here.
            override fun areContentsTheSame(
                oldItem: BasicRoomEntity, newItem: BasicRoomEntity) = oldItem == newItem
        }
    }

    class MyViewHolder(view: View) : BindingViewHolder<ItemBasicroomBinding>(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        Log.d(TAG, "onCreateViewHolder(viewType: $viewType)")
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_basicroom,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder(position: $position)")
        holder.binding.item = getItem(position)
    }
}
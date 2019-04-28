package com.udovyk.users.ui.userslist.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.udovyk.users.R
import com.udovyk.users.network.model.ResultsItem


class ItemAdapter : PagedListAdapter<ResultsItem, ItemAdapter.ItemViewHolder>(DIFF_CALLBACK) {

    @NonNull
    override
    fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ItemViewHolder(view)
    }

    override
    fun onBindViewHolder(@NonNull holder: ItemViewHolder, position: Int) {
        val item = getItem(position)

        if (item != null) {
            val name = item.name!!.first.plus(item.name.last)
            holder.tvName.text = name
            Glide.with(holder.itemView.context)
                .load(item.picture!!.medium)
                .apply(RequestOptions().circleCrop())
                .into(holder.tvAvatar)
        }
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvName: TextView = itemView.findViewById(R.id.tvName)
        var tvAvatar: ImageView = itemView.findViewById(R.id.imAvatar)

    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ResultsItem>() {
            override
            fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
                return oldItem.id === newItem.id
            }

            override
            fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
                return oldItem.equals(newItem)
            }
        }
    }
}
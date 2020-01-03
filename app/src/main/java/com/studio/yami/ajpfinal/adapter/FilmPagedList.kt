package com.studio.yami.ajpfinal.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.studio.yami.ajpfinal.R
import com.studio.yami.ajpfinal.data.local.Favorite
import com.studio.yami.ajpfinal.databinding.ItemLayoutBinding
import com.studio.yami.ajpfinal.ui.activity.DetailActivity
import com.studio.yami.ajpfinal.ui.activity.DetailActivity.Companion.EXTRA_DATA


class FilmPagedList : PagedListAdapter<Favorite, FilmPagedList.FilmHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmHolder {
        val binding: ItemLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_layout, parent, false
        )
        return FilmHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: FilmHolder, position: Int) {

        val list = getItem(position)
        if (list != null) {
            holder.onBind(list)
        }

    }

    companion object {

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Favorite>() {
            override fun areItemsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
                return oldItem.id == oldItem.id
            }

            override fun areContentsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
                return oldItem == newItem
            }
        }

    }

    class FilmHolder(private val context: Context?, private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var favorite: Favorite

        init {
            binding.root.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(EXTRA_DATA, favorite)
                context?.startActivity(intent)
            }
        }

        fun onBind(film: Favorite) {
            binding.film = film
            favorite = film
        }

    }

}
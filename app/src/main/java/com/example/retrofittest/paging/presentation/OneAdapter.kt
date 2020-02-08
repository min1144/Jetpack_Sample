package com.example.retrofittest.paging.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofittest.R
import com.example.retrofittest.databinding.ItemMovieBinding
import com.example.retrofittest.paging.data.remote.MovieItem

class OneAdapter(): PagedListAdapter<MovieItem, OneAdapter.MyViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OneAdapter.MyViewHolder {
        val binding = DataBindingUtil.inflate<ItemMovieBinding>(LayoutInflater.from(parent.context),
            R.layout.item_movie, parent, false)
        return MyViewHolder(binding)
    }

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position) as MovieItem
        holder.onBind(data)
    }

    inner class MyViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(movieItem: MovieItem) {
            binding.movieItem = movieItem
            binding.executePendingBindings()
        }
    }

    class DiffCallback: DiffUtil.ItemCallback<MovieItem>() {
        override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
            return oldItem.title === newItem.title
        }

        override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
            return oldItem == newItem
        }
    }
}
package com.example.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ItemCareOnBinding

class CareOnRecyclerViewAdapter(private val videoList: ArrayList<Video>, private val listener: OnVideoItemClickListener) : RecyclerView.Adapter<CareOnRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(binding: ItemCareOnBinding) : RecyclerView.ViewHolder(binding.root) {
        val videoName = binding.videoName
        val videoThumbnail = binding.videoThumbnail
        val root = binding.root
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemCareOnBinding = ItemCareOnBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val video = videoList[position]

        holder.videoName.text = video.title
        holder.videoThumbnail.setBackgroundResource(R.drawable.dog)

        holder.root.setOnClickListener{
            listener.onItemClick(video)
        }
    }

    override fun getItemCount(): Int {
        return videoList.size
    }
}
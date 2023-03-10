package com.example.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ItemLongTodoBinding
import com.example.todolist.db.LongTermGoalEntity

class LTodoRecyclerViewAdapter(private val lTodoList : ArrayList<LongTermGoalEntity>) : RecyclerView.Adapter<LTodoRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(binding : ItemLongTodoBinding) : RecyclerView.ViewHolder(binding.root) {
        val tv_title = binding.tvTitle
        val emoticon = binding.emoticon
        val root = binding.root
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding : ItemLongTodoBinding = ItemLongTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todoData = lTodoList[position]

        holder.tv_title.text = todoData.title
        holder.emoticon.text = todoData.emoticon
    }

    override fun getItemCount(): Int {
        return lTodoList.size
    }
}
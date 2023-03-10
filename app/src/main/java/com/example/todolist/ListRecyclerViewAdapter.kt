package com.example.todolist

import android.content.Context
import android.graphics.Paint
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.text.buildSpannedString
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ItemTodoBinding
import com.example.todolist.databinding.ItemTodoListBinding
import com.example.todolist.db.TodoEntity
import kotlinx.coroutines.NonDisposableHandle.parent

class ListRecyclerViewAdapter(private val todoList : ArrayList<TodoEntity>): RecyclerView.Adapter<ListRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(binding : ItemTodoListBinding) : RecyclerView.ViewHolder(binding.root){
        val tv_title = binding.tvTitle
        val btnCheck = binding.btnCheck
        val root = binding.root
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemTodoListBinding = ItemTodoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todoData = todoList[position]
        val color = ContextCompat.getColor(holder.itemView.context, R.color.mint)

        holder.tv_title.text = todoData.title

        if (todoData.isDone == false) {
            val ssb = SpannableStringBuilder(holder.tv_title.text)

            holder.btnCheck.setBackgroundResource(R.drawable.todo_incomplete_icon)
            ssb.setSpan(BackgroundColorSpan(color), 0, holder.tv_title.text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            holder.tv_title.text = ssb
        } else {
            holder.btnCheck.setBackgroundResource(R.drawable.todo_complete_icon)
        }
    }

    override fun getItemCount(): Int {//몇개의 목록을 만들지 반환
        return todoList.size
    }
}
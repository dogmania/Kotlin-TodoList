package com.example.todolist

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ItemTodoBinding
import com.example.todolist.db.TodoEntity

class TodoRecyclerViewAdapter(private val todoList : ArrayList<TodoEntity>,
                              private val listener : OnItemLongClickListener, private val buttonListener: OnButtonClickListener)
    : RecyclerView.Adapter<TodoRecyclerViewAdapter.MyViewHolder>() {

    inner class MyViewHolder(binding : ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {
            //목록의 개별 항목 레이아웃을 포함하는 view 래퍼로, 각 목록 레이아웃에 필요한 기능들을 구현하는 공간. item 레이아웃에 버튼이 있다면 리스너를 여기서 구현한다.
            //private val todayTodoActivity = TodayTodoActivity.getInstance()
            val tv_title = binding.tvTitle
            val btnCheck = binding.btnCheck
            val root = binding.root//xml 파일에서 최상단의 부모를 root라는 property로 넘겨준다.

            init {
                btnCheck.setOnClickListener {
                    buttonListener.onButtonClick(adapterPosition)
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {//어떤 레이아웃 껍데기를 만들 것인지 반환
        val binding: ItemTodoBinding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {//생성된 뷰(껍데기)에 어떤 데이터를 넣을 것인지 설정
        val todoData = todoList[position]

        holder.tv_title.text = todoData.title

        holder.btnCheck.isChecked = todoData.isDone

        if(todoData.isDone == false) {
            holder.tv_title.paintFlags = holder.tv_title.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            holder.tv_title.setTextColor(holder.itemView.context.getColor(R.color.black))
        }else {
            holder.tv_title.paintFlags =  holder.tv_title.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            holder.tv_title.setTextColor(holder.itemView.context.getColor(R.color.gray))
        }

        holder.btnCheck.setBackgroundResource(R.drawable.btn_selector) // set the background of the button to the selector resource

        holder.root.setOnLongClickListener {
            listener.onLongClick(position)
            false
        }
    }

    override fun getItemCount(): Int {//몇개의 목록을 만들지 반환
        return todoList.size
    }
}
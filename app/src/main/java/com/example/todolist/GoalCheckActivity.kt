package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityGoalCheckBinding
import com.example.todolist.db.*

class GoalCheckActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGoalCheckBinding
    //아래의 변수는 오늘 할 일에 대한 변수들
    private lateinit var db : AppDatabase
    private lateinit var todoDao : TodoDao
    private lateinit var todoList : ArrayList<TodoEntity>
    private lateinit var adapter : ListRecyclerViewAdapter
    //아래의 변수는 장기목표에 대한 변수들
    private lateinit var lTodoList: ArrayList<LongTermGoalEntity>
    private lateinit var lDb : AppDatabase
    private lateinit var lTodoDao : LongTermGoalDao
    private lateinit var lAdapter : LTodoRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoalCheckBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnAddGoal.setOnClickListener{
            val intent = Intent(this, CurrentLongTermGoal::class.java)
            startActivity(intent)
        }

        binding.btnAddFixedGoal.setOnClickListener {
            val intent = Intent(this, ModifyTodayTodoActivity::class.java)
            startActivity(intent)
        }

        db = AppDatabase.getInstance(this)!!
        todoDao = db.getTodoDao()

        lDb = AppDatabase.getInstance(this)!!
        lTodoDao = lDb.getLongTermGoal()

        getAllTodoList()
        getAllLongTodoList()
    }

    private fun getAllTodoList() {
        Thread {
            todoList = ArrayList(todoDao.getAll())
            setRecyclerView()
        }.start()
    }

    private fun getAllLongTodoList() {
        Thread{
            lTodoList = ArrayList(lTodoDao.getAll())
            setLongRecyclerView()
        }.start()
    }

    private fun setRecyclerView() {
        runOnUiThread {
            adapter = ListRecyclerViewAdapter(todoList)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
        }
    }

    private fun setLongRecyclerView() {
        runOnUiThread {
            lAdapter = LTodoRecyclerViewAdapter(lTodoList)
            binding.longGoalRecyclerview.adapter = lAdapter
            binding.longGoalRecyclerview.layoutManager = LinearLayoutManager(this)
        }
    }

    override fun onRestart() {
        super.onRestart()
        getAllTodoList()
        getAllLongTodoList()
    }
}
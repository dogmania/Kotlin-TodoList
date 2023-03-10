package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityCurrentLongTermGoalBinding
import com.example.todolist.db.AppDatabase
import com.example.todolist.db.LongTermGoalDao
import com.example.todolist.db.LongTermGoalEntity

class CurrentLongTermGoal : AppCompatActivity() {
    private lateinit var binding: ActivityCurrentLongTermGoalBinding

    private lateinit var db : AppDatabase
    private lateinit var longTermGoalDao: LongTermGoalDao
    private lateinit var goalList : ArrayList<LongTermGoalEntity>
    private lateinit var adapter : LTodoRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCurrentLongTermGoalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddGoal.setOnClickListener {
            intent = Intent(this, AddLongTermGoalActivity::class.java)
            startActivity(intent)
        }

        db = AppDatabase.getInstance(this)!!
        longTermGoalDao = db.getLongTermGoal()

        getAllGoal()
    }

    private fun getAllGoal() {
        Thread {
            goalList = ArrayList(longTermGoalDao.getAll())
            setRecyclerView()
        }.start()
    }

    private fun setRecyclerView() {
        runOnUiThread {
            adapter = LTodoRecyclerViewAdapter(goalList)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
        }
    }

    override fun onRestart() {
        super.onRestart()
        getAllGoal()
    }
}
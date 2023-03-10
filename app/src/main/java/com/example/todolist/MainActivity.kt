package com.example.todolist

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.db.AppDatabase
import com.example.todolist.db.TodoDao
import com.example.todolist.db.TodoEntity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var db : AppDatabase
    private lateinit var todoDao : TodoDao
    private lateinit var todoList : ArrayList<TodoEntity>
    private lateinit var adapter : TodoRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalendar.setOnClickListener{
            val intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
        }

        binding.btnLookOn.setOnClickListener {
            val intent = Intent(this, LookOnActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoalCheck.setOnClickListener {
            val intent = Intent(this, GoalCheckActivity::class.java)
            startActivity(intent)
        }

        binding.btnCare.setOnClickListener {
            val intent = Intent(this, CareActivity::class.java)
            startActivity(intent)
        }

        db = AppDatabase.getInstance(this)!!
        todoDao = db.getTodoDao()

    }
}
package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityCalendarBinding
import com.example.todolist.db.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class CalendarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalendarBinding

    private lateinit var db: AppDatabase
    private lateinit var adapter: ListRecyclerViewAdapter
    private lateinit var todoDao: TodoDao
    private lateinit var todoList: ArrayList<TodoEntity>

    private lateinit var longTermGoalDao : LongTermGoalDao
    private lateinit var goalList : ArrayList<LongTermGoalEntity>
    private lateinit var lAdapter : LTodoRecyclerViewAdapter

    val today = Calendar.getInstance()
    val month = (today.get(Calendar.MONTH) + 1).toString()
    val day = today.get(Calendar.DAY_OF_MONTH).toString()
    val dayOfWeek = today.get(Calendar.DAY_OF_WEEK)
    val formattedDate = "$month.$day"
    val dayList = arrayListOf("일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일")
    val currentTime = Calendar.getInstance().time
    val hourOfDay = currentTime.hours
    val amOrPm = if (hourOfDay < 12) "오전" else "오후"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTemp.setOnClickListener {
            val intent = Intent(this, TodayTodoActivity::class.java)
            startActivity(intent)
        }

        db = AppDatabase.getInstance(this)!!
        todoDao = db.getTodoDao()
        longTermGoalDao = db.getLongTermGoal()

        binding.todayDate.text = formattedDate
        binding.todayDay.text = dayList[dayOfWeek - 1]
        binding.amPm.text = amOrPm

        getAllTodoList()
        getAllGoal()
    }

    private fun getAllTodoList() {
        Thread {
            todoList = ArrayList(todoDao.getAll())
            setRecyclerView()
        }.start()
    }

    private fun getAllGoal() {
        Thread{
            goalList = ArrayList(longTermGoalDao.getAll())
            setGoalRecyclerView()
        }.start()
    }

    private fun setRecyclerView() {
        runOnUiThread {
            adapter = ListRecyclerViewAdapter(todoList)
            binding.todayTodoRecycler.adapter = adapter
            binding.todayTodoRecycler.layoutManager = LinearLayoutManager(this)
        }
    }

    private fun setGoalRecyclerView() {
        runOnUiThread {
            lAdapter = LTodoRecyclerViewAdapter(goalList)
            binding.longTermGoalRecyclerView.adapter = lAdapter
            binding.longTermGoalRecyclerView.layoutManager = LinearLayoutManager(this)
        }
    }

    override fun onRestart() {
        super.onRestart()
        getAllTodoList()
    }
}
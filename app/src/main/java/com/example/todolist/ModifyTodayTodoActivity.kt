package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todolist.databinding.ActivityModifyTodayTodoBinding

class ModifyTodayTodoActivity : AppCompatActivity() {
    private lateinit var binding : ActivityModifyTodayTodoBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModifyTodayTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}
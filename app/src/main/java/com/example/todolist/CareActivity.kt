package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todolist.databinding.ActivityCareBinding
import com.example.todolist.databinding.ActivityGoalCheckBinding

class CareActivity : AppCompatActivity() {
    lateinit var binding: ActivityCareBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCareBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnDown.setOnClickListener {
            val intent = Intent(this, CareOnActivity::class.java)
            startActivity(intent)

            overridePendingTransition(R.anim.slide_up_enter, R.anim.slide_up_exit)
        }
    }
}
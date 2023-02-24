package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todolist.databinding.ActivityLookOnBinding

class LookOnActivity : AppCompatActivity() {
    lateinit var binding: ActivityLookOnBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLookOnBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnPencil.setOnClickListener {
            val intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
        }

        binding.btnCheckList.setOnClickListener {
            val intent = Intent(this, GoalCheckActivity::class.java)
            startActivity(intent)
        }

        binding.btnHeart.setOnClickListener {
            val intent = Intent(this, CareActivity::class.java)
            startActivity(intent)
        }
    }
}
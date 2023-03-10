package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.example.todolist.databinding.ActivityLookOnBinding

class LookOnActivity : AppCompatActivity() {
    lateinit var binding: ActivityLookOnBinding

//    val parentLayout = findViewById<FrameLayout>(R.id.frame_layout)
//    val floatLayout = findViewById<LinearLayout>(R.id.float_layout)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLookOnBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
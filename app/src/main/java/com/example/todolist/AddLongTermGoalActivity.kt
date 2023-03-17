package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.todolist.databinding.ActivityAddLongTermGoalBinding
import com.example.todolist.db.AppDatabase
import com.example.todolist.db.LongTermGoalDao
import com.example.todolist.db.LongTermGoalEntity
import java.util.*

class AddLongTermGoalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddLongTermGoalBinding

    private lateinit var db : AppDatabase
    private lateinit var longTermGoalDao : LongTermGoalDao
    private lateinit var periodSpinner : Spinner
    private lateinit var measureSpinner : Spinner

    private var period = 0
    private var measure : Int = 0

    val today = Calendar.getInstance()
    val year = today.get(Calendar.YEAR).toString()
    val month = (today.get(Calendar.MONTH) + 1).toString()
    val day = today.get(Calendar.DAY_OF_MONTH).toString()
    val formattedStartDate = "$year.$month.$day"
    val formattedEndDate = formattedStartDate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddLongTermGoalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.seekBar.isEnabled = false

        db = AppDatabase.getInstance(this)!!
        longTermGoalDao = db.getLongTermGoal()

        periodSpinner = binding.periodSpinner
        measureSpinner = binding.measureSpinner

        val periodAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.period_array,
            android.R.layout.simple_spinner_dropdown_item)

        val measureAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.measure_array,
            android.R.layout.simple_spinner_dropdown_item
        )

        periodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        measureAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        periodSpinner.adapter = periodAdapter
        measureSpinner.adapter = measureAdapter

        periodSpinner.prompt = getString(R.string.spinner_title)
        measureSpinner.prompt = getString(R.string.spinner_title)

        binding.startDateView.text = formattedStartDate
        binding.endDateView.text = formattedEndDate

        binding.btnComplete.setOnClickListener {
            insertGoal()
        }

    }

    private fun insertGoal() {
        val goalTitle = binding.edtTodo.text.toString()
        val emoticon = binding.edtEmoticon.text.toString()

        periodSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                        period = 1
                    }
                    1 -> period = 2
                    2 -> period = 3
                    3 -> period = 4
                    4 -> period = 5
                    5 -> period = 6
                }
            }
        }

        measureSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> measure = 1
                    1 -> measure = 2
                }
            }
        }

        if (goalTitle.isBlank() || emoticon.isBlank()) {
            Toast.makeText(this, "모든 항목을 채워주세요.", Toast.LENGTH_SHORT).show()
        } else {
            Thread {
                longTermGoalDao.insertLongTermGoal(LongTermGoalEntity(null, goalTitle, emoticon, period, measure))
                runOnUiThread{
                    Toast.makeText(this, "추가되었습니다.",
                        Toast.LENGTH_SHORT).show()
                    finish()
                }
            }.start()
        }
    }
}
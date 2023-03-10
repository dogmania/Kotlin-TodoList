package com.example.todolist.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LongTermGoalEntity(
    @PrimaryKey(autoGenerate = true) var id : Int? = null,
    @ColumnInfo(name = "title") var title : String,
    @ColumnInfo(name = "emoticon") var emoticon : String,
    @ColumnInfo(name = "period") var period : Int,
    @ColumnInfo(name = "measure") var measure : Int
)
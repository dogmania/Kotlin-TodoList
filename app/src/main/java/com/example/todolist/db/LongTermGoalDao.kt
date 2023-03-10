package com.example.todolist.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface LongTermGoalDao {
    @Query("SELECT * FROM LongTermGoalEntity")
    fun getAll() : List<LongTermGoalEntity>

    @Insert
    fun insertLongTermGoal(goal : LongTermGoalEntity)

    @Delete
    fun deleteLongTermGoal(goal : LongTermGoalEntity)

    @Update
    fun updateLongTermGoal(goal : LongTermGoalEntity)
}
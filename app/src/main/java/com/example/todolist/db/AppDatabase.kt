package com.example.todolist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(TodoEntity::class, LongTermGoalEntity::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getTodoDao() : TodoDao
    abstract fun getLongTermGoal() : LongTermGoalDao

//    companion object {
//        val databaseName = "db_todo"
//        var appDatabase : AppDatabase? = null
//
//        fun getInstance(context : Context) : AppDatabase? {
//            if (appDatabase == null) {
//                    appDatabase = Room.databaseBuilder(
//                        context,
//                        AppDatabase::class.java,
//                        databaseName
//                    ).build()
//            }
//            return appDatabase
//        }
//    }

    companion object {
        private const val databaseName = "db_todo"
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, databaseName)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
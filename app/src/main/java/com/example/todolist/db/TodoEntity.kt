package com.example.todolist.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity//어떤 구성요소인지 어노테이션을 써서 알려야한다.
data class TodoEntity (
    @PrimaryKey(autoGenerate = true) var id : Int? = null,
    @ColumnInfo(name = "title") var title : String
//    @ColumnInfo(name = "importance") var importance : Int
    )
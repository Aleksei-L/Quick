package com.example.todolist.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todolist.dao.QuickDao
import com.example.todolist.data.Quick

@Database(entities = [Quick::class], version = 1)
abstract class QuickDatabase : RoomDatabase() {
	abstract fun quickDao(): QuickDao
}

package com.example.todolist

import android.app.Application
import androidx.room.Room
import com.example.todolist.dao.QuickDao
import com.example.todolist.db.QuickDatabase

class MyApp : Application() {
	lateinit var globalDao: QuickDao

	override fun onCreate() {
		super.onCreate()
		val db = Room.databaseBuilder(
			applicationContext,
			QuickDatabase::class.java,
			"quick_database"
		).build()
		globalDao = db.quickDao()
	}
}

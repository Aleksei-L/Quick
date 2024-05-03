package com.example.todolist

import android.app.Application
import androidx.room.Room
import com.example.todolist.dao.QuickDao
import com.example.todolist.db.QuickDatabase
import com.example.todolist.repo.QuickRepo

class MyApp : Application() {
	// TODO переписать всё на DI
	lateinit var globalDao: QuickDao
	lateinit var globalQuickRepo: QuickRepo

	override fun onCreate() {
		super.onCreate()
		val db = Room.databaseBuilder(
			applicationContext,
			QuickDatabase::class.java,
			"quick_database"
		).build()
		globalDao = db.quickDao()
		globalQuickRepo = QuickRepo(globalDao)
	}
}

package com.example.todolist.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todolist.data.Quick

@Dao
interface QuickDao {
	@Query("SELECT * FROM Quick")
	fun getAllQuick(): List<Quick>

	@Query("SELECT * FROM Quick WHERE id = :id")
	fun getQuickById(id: Long): Quick

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insertQuick(quick: Quick)

	@Delete
	fun deleteQuick(quick: Quick)
}

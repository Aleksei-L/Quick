package com.example.todolist.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.todolist.data.Quick

@Dao
interface QuickDao {
	@Query("SELECT * FROM Quick")
	fun getAllQuick(): List<Quick>

	@Query("SELECT * FROM Quick WHERE id = :id")
	fun getQuickById(id: Long): Quick

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insertQuick(quick: Quick)

	@Update
	fun updateQuick(quick: Quick)

	@Delete
	fun deleteQuick(quick: Quick)
}

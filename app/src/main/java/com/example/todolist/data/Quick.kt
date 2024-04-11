package com.example.todolist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class PRIORITY {
	LOW, MEDIUM, HIGH
}

@Entity
data class Quick(
	@PrimaryKey(autoGenerate = true) val id: Long,
	val title: String,
	val description: String,
	val priority: PRIORITY
)

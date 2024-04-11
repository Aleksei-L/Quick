package com.example.todolist.data

enum class PRIORITY {
	LOW, MEDIUM, HIGH
}

data class Quick(
	val title: String,
	val description: String,
	val priority: PRIORITY
)

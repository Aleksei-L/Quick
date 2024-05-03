package com.example.todolist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.repo.QuickRepo

class MainViewModelFactory(private val repo: QuickRepo) : ViewModelProvider.Factory {
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		return MainViewModel(repo) as T
	}
}

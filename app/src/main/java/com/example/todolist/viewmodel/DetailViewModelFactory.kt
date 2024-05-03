package com.example.todolist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.repo.QuickRepo

class DetailViewModelFactory(private val repo: QuickRepo) : ViewModelProvider.Factory {
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		return DetailViewModel(repo) as T
	}
}

package com.example.todolist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.repo.QuickRepo

class CreateQuickViewModelFactory(private val repo: QuickRepo) : ViewModelProvider.Factory {
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		return CreateQuickViewModel(repo) as T
	}
}

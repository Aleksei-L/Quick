package com.example.todolist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.Quick
import com.example.todolist.repo.QuickRepo
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel(private val quickRepo: QuickRepo) : ViewModel() {
	private val privateData = MutableLiveData<List<Quick>>()
	val data: LiveData<List<Quick>> = privateData

	fun refresh() = viewModelScope.launch {
		privateData.postValue(quickRepo.getAllQuick())
	}

	override fun onCleared() {
		super.onCleared()
		viewModelScope.cancel()
	}
}

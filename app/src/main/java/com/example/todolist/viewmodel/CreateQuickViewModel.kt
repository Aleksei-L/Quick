package com.example.todolist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.Quick
import com.example.todolist.repo.QuickRepo
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class CreateQuickViewModel(private val quickRepo: QuickRepo) : ViewModel() {
	private val privateData = MutableLiveData<Quick?>()
	val data = privateData

	fun getQuickById(id: Long) = viewModelScope.launch {
		val thisQuick = quickRepo.getQuickById(id)
		if (thisQuick != null) {
			privateData.postValue(thisQuick)
		}
		/*else
			toast.show()*/
	}

	fun insertQuick(quick: Quick) = viewModelScope.launch {
		quickRepo.insertQuick(quick)
	}

	fun updateQuick(quick: Quick) = viewModelScope.launch {
		quickRepo.updateQuick(quick)
	}

	override fun onCleared() {
		super.onCleared()
		viewModelScope.cancel()
	}
}

package com.example.todolist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.Quick
import com.example.todolist.repo.QuickRepo
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class DetailViewModel(private val quickRepo: QuickRepo) : ViewModel() {
	// TODO тост вернётся чуть позже!
	/*private val toast = Toast.makeText(
		application.applicationContext,
		"Ошибка: не удалось удалить заметку!",
		Toast.LENGTH_LONG
	)*/
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

	fun deleteQuick(quickId: Long) = viewModelScope.launch {
		if (quickId != -1L) {
			val quickForDelete = quickRepo.getQuickById(quickId)
			if (quickForDelete != null)
				quickRepo.deleteQuick(quickForDelete)
		} /*else
			toast.show()*/
	}

	fun refresh(id: Long) = viewModelScope.launch {
		privateData.postValue(quickRepo.getQuickById(id))
	}

	override fun onCleared() {
		super.onCleared()
		viewModelScope.cancel()
	}
}

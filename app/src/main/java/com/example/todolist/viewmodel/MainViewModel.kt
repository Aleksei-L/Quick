package com.example.todolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todolist.MyApp
import com.example.todolist.data.Quick
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val application: Application) : AndroidViewModel(application) {
	private val mutableData = MutableLiveData<List<Quick>>()
	val data: LiveData<List<Quick>> = mutableData

	fun refresh() = CoroutineScope(Dispatchers.IO).launch {
		val app = application.applicationContext as MyApp
		val dao = app.globalDao
		mutableData.postValue(dao.getAllQuick())
	}
}

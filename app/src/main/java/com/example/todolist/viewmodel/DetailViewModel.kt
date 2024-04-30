package com.example.todolist.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.example.todolist.MyApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : AndroidViewModel(application) {
	private val toast = Toast.makeText(
		application.applicationContext,
		"Ошибка: не удалось удалить заметку!",
		Toast.LENGTH_LONG
	)
	private val app = application.applicationContext as MyApp
	private val dao = app.globalDao

	fun deleteQuick(quickId: Long) = CoroutineScope(Dispatchers.IO).launch {
		if (quickId != -1L) {
			val quickForDelete = dao.getQuickById(quickId)
			if (quickForDelete != null)
				dao.deleteQuick(quickForDelete)
		} else
			toast.show()
	}
}

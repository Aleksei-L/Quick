package com.example.todolist.repo

import com.example.todolist.dao.QuickDao
import com.example.todolist.data.Quick
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuickRepo(private val quickDao: QuickDao) {
	suspend fun getQuickById(id: Long): Quick? {
		return withContext(Dispatchers.IO) {
			return@withContext quickDao.getQuickById(id)
		}
	}

	suspend fun getAllQuick(): List<Quick> {
		return withContext(Dispatchers.IO) {
			return@withContext quickDao.getAllQuick()
		}
	}

	suspend fun insertQuick(quick: Quick) = withContext(Dispatchers.IO) {
		quickDao.insertQuick(quick)
	}

	suspend fun updateQuick(quick: Quick) = withContext(Dispatchers.IO) {
		quickDao.updateQuick(quick)
	}

	suspend fun deleteQuick(quick: Quick) = withContext(Dispatchers.IO) {
		quickDao.deleteQuick(quick)
	}
}
package com.example.todolist.repo

import com.example.todolist.data.PRIORITY
import com.example.todolist.data.Quick

class QuickRepo {
	companion object {
		fun getQuickList(): MutableList<Quick> {
			return mutableListOf(
				Quick("Заголовок", "Описание", PRIORITY.MEDIUM),
				Quick("Рыба", "Рыба рыба рыба рыба рыба рыба", PRIORITY.HIGH),
				Quick("Что-то сделать", "Сделай что-нибудь!", PRIORITY.LOW),
				Quick("Что-то сделать", "Сделай что-нибудь!", PRIORITY.LOW),
				Quick("Что-то сделать", "Сделай что-нибудь!", PRIORITY.LOW),
				Quick("Что-то сделать", "Сделай что-нибудь!", PRIORITY.LOW),
				Quick("Что-то сделать", "Сделай что-нибудь!", PRIORITY.LOW),
				Quick("Что-то сделать", "Сделай что-нибудь!", PRIORITY.LOW),
				Quick("Что-то сделать", "Сделай что-нибудь!", PRIORITY.LOW),
				Quick("Что-то сделать", "Сделай что-нибудь!", PRIORITY.LOW),
				Quick("Что-то сделать", "Сделай что-нибудь!", PRIORITY.LOW)
			)
		}
	}
}

package com.example.todolist.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.room.Room
import com.example.todolist.R
import com.example.todolist.data.PRIORITY
import com.example.todolist.data.Quick
import com.example.todolist.db.QuickDatabase
import com.google.android.material.button.MaterialButtonToggleGroup
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateQuickActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_create_quick)

		val toolbar = findViewById<Toolbar>(R.id.toolbar)
		setSupportActionBar(toolbar)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)

		val createQuickButton = findViewById<Button>(R.id.create)
		val title = findViewById<EditText>(R.id.edit_title)
		val description = findViewById<EditText>(R.id.edit_description)
		val priority = findViewById<MaterialButtonToggleGroup>(R.id.segmented_button_group)

		createQuickButton.setOnClickListener {
			//TODO вынести базу данных в сингтон
			val db = Room.databaseBuilder(
				this,
				QuickDatabase::class.java,
				"quick_database"
			).build()

			CoroutineScope(Dispatchers.IO).launch {
				val quick = db.quickDao()
				quick.insertQuick(
					Quick(
						0,
						title.text.toString(),
						description.text.toString(),
						when (priority.checkedButtonId) {
							R.id.priority_low_button -> PRIORITY.LOW
							R.id.priority_medium_button -> PRIORITY.MEDIUM
							R.id.priority_high_button -> PRIORITY.HIGH
							else -> PRIORITY.MEDIUM //TODO убрать это
						}
					)
				)
			}

			onBackPressed() //TODO поменять на нормальный способ возврата к предыдущий activity
		}
	}
}

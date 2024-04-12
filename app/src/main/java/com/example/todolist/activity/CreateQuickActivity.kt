package com.example.todolist.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.todolist.R
import com.example.todolist.data.PRIORITY
import com.example.todolist.data.Quick
import com.example.todolist.db.QuickDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateQuickActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_create_quick)

		val createQuickButton = findViewById<Button>(R.id.create)
		val title = findViewById<EditText>(R.id.edit_title)
		val description = findViewById<EditText>(R.id.edit_description)


		createQuickButton.setOnClickListener {
			val db = Room.databaseBuilder(
				this,
				QuickDatabase::class.java,
				"quick_database"
			).build()

			val job = CoroutineScope(Dispatchers.IO).launch {
				val quick = db.quickDao()
				quick.insertQuick(
					Quick(
						0,
						title.text.toString(),
						description.text.toString(),
						PRIORITY.MEDIUM
					)
				)
			}

			onBackPressed()
		}

	}
}

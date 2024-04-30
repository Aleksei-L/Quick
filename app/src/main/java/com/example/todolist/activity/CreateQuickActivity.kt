package com.example.todolist.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.todolist.MyApp
import com.example.todolist.R
import com.example.todolist.data.PRIORITY
import com.example.todolist.data.Quick
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
		toolbar.setNavigationOnClickListener {
			onBackPressedDispatcher.onBackPressed()
		}

		// Проверка на переход с DetailActivity
		val editable = intent.getStringExtra(DetailActivity.EDIT_FLAG_EXTRA)

		val createQuickButton = findViewById<Button>(R.id.create)
		createQuickButton.text = editable ?: resources.getText(R.string.create_quick_button)

		val title = findViewById<EditText>(R.id.edit_title)
		val description = findViewById<EditText>(R.id.edit_description)
		val priority = findViewById<MaterialButtonToggleGroup>(R.id.segmented_button_group)

		createQuickButton.setOnClickListener {
			if (editable == null) {
				CoroutineScope(Dispatchers.IO).launch {
					val app = application as MyApp
					val dao = app.globalDao
					dao.insertQuick(
						Quick(
							0,
							title.text.toString(),
							description.text.toString(),
							when (priority.checkedButtonId) {
								R.id.priority_low_button -> PRIORITY.LOW
								R.id.priority_high_button -> PRIORITY.HIGH
								else -> PRIORITY.MEDIUM
							}
						)
					)
				}
			} else {
				CoroutineScope(Dispatchers.IO).launch {
					val app = application as MyApp
					val dao = app.globalDao
					dao.updateQuick(
						Quick(
							intent.getLongExtra(MainActivity.ID_EXTRA, 0),
							title.text.toString(),
							description.text.toString(),
							when (priority.checkedButtonId) {
								R.id.priority_low_button -> PRIORITY.LOW
								R.id.priority_high_button -> PRIORITY.HIGH
								else -> PRIORITY.MEDIUM
							}
						)
					)
				}
			}

			finish()
		}
	}
}

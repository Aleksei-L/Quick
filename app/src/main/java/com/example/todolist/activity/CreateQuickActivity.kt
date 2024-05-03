package com.example.todolist.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.MyApp
import com.example.todolist.R
import com.example.todolist.data.PRIORITY
import com.example.todolist.data.Quick
import com.example.todolist.viewmodel.CreateQuickViewModel
import com.example.todolist.viewmodel.CreateQuickViewModelFactory
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonToggleGroup

class CreateQuickActivity : AppCompatActivity() {
	private lateinit var vm: CreateQuickViewModel

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_create_quick)

		val toolbar = findViewById<Toolbar>(R.id.toolbar)
		setSupportActionBar(toolbar)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)
		toolbar.setNavigationOnClickListener {
			onBackPressedDispatcher.onBackPressed()
		}

		val app = application as MyApp

		vm = ViewModelProvider(
			this,
			CreateQuickViewModelFactory(app.globalQuickRepo)
		)[CreateQuickViewModel::class.java]

		// Проверка на переход с DetailActivity
		val editable = intent.getStringExtra(DetailActivity.EDIT_FLAG_EXTRA)

		val createQuickButton = findViewById<Button>(R.id.create)
		createQuickButton.text = editable ?: resources.getText(R.string.create_quick_button)

		val title = findViewById<EditText>(R.id.edit_title)
		val description = findViewById<EditText>(R.id.edit_description)
		val priority = findViewById<MaterialButtonToggleGroup>(R.id.segmented_button_group)
		val left = findViewById<MaterialButton>(R.id.priority_low_button)
		val mid = findViewById<MaterialButton>(R.id.priority_medium_button)
		val right = findViewById<MaterialButton>(R.id.priority_high_button)

		val quickId = intent.getLongExtra(MainActivity.ID_EXTRA, -1)
		if (quickId != -1L)
			vm.getQuickById(quickId)
		vm.data.observe(this) {
			title.setText(title.text.toString() + it?.title)
			description.setText(description.text.toString() + it?.description)
			when (it?.priority) {
				PRIORITY.LOW -> {
					left.isChecked = true
					mid.isChecked = false
					right.isChecked = false
				}

				PRIORITY.MEDIUM -> {
					left.isChecked = false
					mid.isChecked = true
					right.isChecked = false
				}

				PRIORITY.HIGH -> {
					left.isChecked = false
					mid.isChecked = false
					right.isChecked = true
				}

				else -> {}
			}
		}

		createQuickButton.setOnClickListener {
			if (editable == null)
				vm.insertQuick(
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
			else
				vm.updateQuick(
					Quick(
						quickId,
						title.text.toString(),
						description.text.toString(),
						when (priority.checkedButtonId) {
							R.id.priority_low_button -> PRIORITY.LOW
							R.id.priority_high_button -> PRIORITY.HIGH
							else -> PRIORITY.MEDIUM
						}
					)
				)
			finish()
		}
	}
}

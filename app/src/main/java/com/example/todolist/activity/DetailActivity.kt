package com.example.todolist.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.R
import com.example.todolist.data.PRIORITY

class DetailActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_item)

		val title = intent.getStringExtra(MainActivity.TITLE_EXTRA)
		val desc = intent.getStringExtra(MainActivity.DESC_EXTRA)
		val priority = intent.getStringExtra(MainActivity.PRIORITY_EXTRA)

		val titleView = findViewById<TextView>(R.id.info_title)
		val descView = findViewById<TextView>(R.id.info_description)
		val priorityView = findViewById<TextView>(R.id.info_priority)

		titleView.text = title
		descView.text = desc
		priorityView.text = "$priority"

	}
}
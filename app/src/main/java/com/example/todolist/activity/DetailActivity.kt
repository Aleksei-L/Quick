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

		val intent = intent
		val title = intent.getStringExtra("title")
		val desc = intent.getStringExtra("description")
		val priority = intent.getStringExtra("priority")

		val titleView = findViewById<TextView>(R.id.info_title)
		val descView = findViewById<TextView>(R.id.info_description)
		val priorityView = findViewById<TextView>(R.id.info_priority)

		titleView.text = title
		descView.text = desc
		priorityView.text = "$priority"

	}
}
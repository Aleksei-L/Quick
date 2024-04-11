package com.example.todolist.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.todolist.R
import com.example.todolist.adapter.QuickItemAdapter
import com.example.todolist.data.Quick
import com.example.todolist.db.QuickDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
	private lateinit var data: List<Quick>

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val db = Room.databaseBuilder(
			this,
			QuickDatabase::class.java,
			"quick_database"
		).build()

		val job = CoroutineScope(Dispatchers.IO).launch {
			val quick = db.quickDao()
			data = quick.getAllQuick()
		}

		val action = object : QuickItemAdapter.OnItemClickListener {
			override fun onItemClick(item: Quick, position: Int) {
				val intent = Intent(this@MainActivity, DetailActivity::class.java)
				intent.putExtra(TITLE_EXTRA, item.title)
				intent.putExtra(DESC_EXTRA, item.description)
				intent.putExtra(PRIORITY_EXTRA, item.priority.name)
				startActivity(intent)
			}
		}
		val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
		recyclerView.layoutManager = LinearLayoutManager(this)
		while (!job.isCompleted) {
		}
		recyclerView.adapter = QuickItemAdapter(data, action)
	}

	companion object {
		const val TITLE_EXTRA = "title"
		const val DESC_EXTRA = "desc"
		const val PRIORITY_EXTRA = "priority"
	}
}

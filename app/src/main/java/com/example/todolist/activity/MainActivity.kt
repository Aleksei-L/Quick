package com.example.todolist.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
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

		val toolbar = findViewById<Toolbar>(R.id.main_toolbar)
		setSupportActionBar(toolbar)
	}

	override fun onStart() {
		super.onStart()
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

	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		menuInflater.inflate(R.menu.main_menu, menu)
		return super.onCreateOptionsMenu(menu)
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		return when (item.itemId) {
			R.id.create_quick -> {
				startActivity(Intent(this, CreateQuickActivity::class.java))
				true
			}

			else -> super.onOptionsItemSelected(item)
		}
	}

	companion object {
		const val TITLE_EXTRA = "title"
		const val DESC_EXTRA = "desc"
		const val PRIORITY_EXTRA = "priority"
	}
}

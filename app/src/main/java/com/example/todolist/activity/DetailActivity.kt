package com.example.todolist.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.room.Room
import com.example.todolist.R
import com.example.todolist.db.QuickDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_detail)

		val toolbar = findViewById<Toolbar>(R.id.toolbar)
		setSupportActionBar(toolbar)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)

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

	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		menuInflater.inflate(R.menu.menu_detail, menu)
		return super.onCreateOptionsMenu(menu)
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		return when (item.itemId) {
			R.id.edit_quick -> {
				//startActivity(Intent(this, CreateQuickActivity::class.java))
				true
			}

			R.id.delete_quick -> {
				//TODO вынести базу данных в сингтон
				val db = Room.databaseBuilder(
					this,
					QuickDatabase::class.java,
					"quick_database"
				).build()
				CoroutineScope(Dispatchers.IO).launch {
					val quick = db.quickDao()
					quick.deleteQuick(
						quick.getQuickById(
							intent.getLongExtra(
								MainActivity.ID_EXTRA,
								0 //TODO предусмотреть чтобы операция отменялась если getQuickById ничего не нашла
							)
						)
					)
				}
				onBackPressed() //TODO поменять на нормальный способ возврата к предыдущий activity
				true
			}

			else -> super.onOptionsItemSelected(item)
		}
	}
}

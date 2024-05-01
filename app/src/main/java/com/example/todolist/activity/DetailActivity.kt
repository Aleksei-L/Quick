package com.example.todolist.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.R
import com.example.todolist.viewmodel.DetailViewModel

class DetailActivity : AppCompatActivity() {
	private lateinit var vm: DetailViewModel

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_detail)

		vm = ViewModelProvider(this)[DetailViewModel::class.java]

		val toolbar = findViewById<Toolbar>(R.id.toolbar)
		setSupportActionBar(toolbar)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)
		toolbar.setNavigationOnClickListener {
			onBackPressedDispatcher.onBackPressed()
		}

		vm.getQuickById(intent.getLongExtra(MainActivity.ID_EXTRA, -1))

		val titleView = findViewById<TextView>(R.id.info_title)
		val descView = findViewById<TextView>(R.id.info_description)
		val priorityView = findViewById<TextView>(R.id.info_priority)

		vm.data.observe(this) {
			titleView.text = vm.data.value?.title
			descView.text = vm.data.value?.description
			priorityView.text = vm.data.value?.priority.toString()
		}
	}

	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		menuInflater.inflate(R.menu.menu_detail, menu)
		return super.onCreateOptionsMenu(menu)
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		return when (item.itemId) {
			R.id.edit_quick -> {
				val myIntent = Intent(this, CreateQuickActivity::class.java)
				myIntent.putExtra(EDIT_FLAG_EXTRA, "Изменить заметку")
				myIntent.putExtra(
					MainActivity.ID_EXTRA,
					intent.getLongExtra(MainActivity.ID_EXTRA, 0)
				) //TODO убрать этот 0
				startActivity(myIntent)
				true
			}

			R.id.delete_quick -> {
				vm.deleteQuick(intent.getLongExtra(MainActivity.ID_EXTRA, -1L))
				finish()
				true
			}

			else -> super.onOptionsItemSelected(item)
		}
	}

	companion object {
		const val EDIT_FLAG_EXTRA = "edit_flag"
	}
}

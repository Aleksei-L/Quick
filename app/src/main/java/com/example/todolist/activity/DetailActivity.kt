package com.example.todolist.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.MyApp
import com.example.todolist.R
import com.example.todolist.data.PRIORITY
import com.example.todolist.viewmodel.DetailViewModel
import com.example.todolist.viewmodel.DetailViewModelFactory

class DetailActivity : AppCompatActivity() {
	private lateinit var vm: DetailViewModel

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_detail)

		val app = application as MyApp

		vm = ViewModelProvider(
			this,
			DetailViewModelFactory(app.globalQuickRepo)
		)[DetailViewModel::class.java]

		val toolbar = findViewById<Toolbar>(R.id.toolbar)
		setSupportActionBar(toolbar)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)
		toolbar.setNavigationOnClickListener {
			onBackPressedDispatcher.onBackPressed()
		}

		vm.getQuickById(intent.getLongExtra(MainActivity.ID_EXTRA, -1))

		val titleView = findViewById<TextView>(R.id.info_title)
		val descView = findViewById<TextView>(R.id.info_description)
		val priorityView = findViewById<ImageView>(R.id.info_priority)

		vm.data.observe(this) {
			titleView.text = vm.data.value?.title
			descView.text = vm.data.value?.description
			priorityView.setImageResource(
				when (vm.data.value?.priority) {
					PRIORITY.LOW -> R.drawable.ic_priority_low
					PRIORITY.HIGH -> R.drawable.ic_priority_high
					else -> 0 // TODO посмотреть в доках можно ли передавать 0
				}
			)
		}
	}

	override fun onResume() {
		super.onResume()
		val id = intent.getLongExtra(MainActivity.ID_EXTRA, -1)
		if (id != -1L)
			vm.refresh(id)
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

package com.example.todolist.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.todolist.R
import com.example.todolist.adapter.QuickItemAdapter
import com.example.todolist.data.Quick
import com.example.todolist.viewmodel.MainViewModel
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class MainActivity : AppCompatActivity() {
	private lateinit var vm: MainViewModel

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		vm = ViewModelProvider(this)[MainViewModel::class.java]

		val refreshLayout = findViewById<SwipeRefreshLayout>(R.id.refresh_layout)
		refreshLayout.setOnRefreshListener {
			vm.refresh()
			refreshLayout.isRefreshing = false
		}

		val toolbar = findViewById<Toolbar>(R.id.toolbar)
		setSupportActionBar(toolbar)

		val fab = findViewById<ExtendedFloatingActionButton>(R.id.fab_create)
		fab.setOnClickListener {
			startActivity(Intent(this, CreateQuickActivity::class.java))
		}

		val action = object : QuickItemAdapter.OnItemClickListener {
			override fun onItemClick(item: Quick, position: Int) {
				val myIntent = Intent(this@MainActivity, DetailActivity::class.java)
				myIntent.putExtra(ID_EXTRA, item.id)
				myIntent.putExtra(TITLE_EXTRA, item.title)
				myIntent.putExtra(DESC_EXTRA, item.description)
				myIntent.putExtra(PRIORITY_EXTRA, item.priority.name)
				startActivity(myIntent)
			}
		}
		val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
		recyclerView.layoutManager = LinearLayoutManager(this)

		recyclerView.adapter = QuickItemAdapter(null, action)

		vm.data.observe(this) {
			recyclerView.adapter = QuickItemAdapter(it, action)
			(recyclerView.adapter as QuickItemAdapter).notifyDataSetChanged()
		}
	}

	override fun onResume() {
		super.onResume()
		vm.refresh()
	}

	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		menuInflater.inflate(R.menu.menu_main, menu)
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
		const val ID_EXTRA = "id"
		const val TITLE_EXTRA = "title"
		const val DESC_EXTRA = "desc"
		const val PRIORITY_EXTRA = "priority"
	}
}

package com.example.todolist.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.adapter.QuickItemAdapter
import com.example.todolist.data.Quick
import com.example.todolist.repo.QuickRepo

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val fakeToDoList = QuickRepo.getQuickList()
		val action = object : QuickItemAdapter.OnItemClickListener {
			override fun onItemClick(item: Quick, position: Int) {
				val intent = Intent(this@MainActivity, DetailActivity::class.java)
				intent.putExtra("title", item.title)
				intent.putExtra("description", item.description)
				intent.putExtra("priority", item.priority.name)
				startActivity(intent)
			}
		}
		val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
		recyclerView.layoutManager = LinearLayoutManager(this)
		recyclerView.adapter = QuickItemAdapter(fakeToDoList, action)
	}
}

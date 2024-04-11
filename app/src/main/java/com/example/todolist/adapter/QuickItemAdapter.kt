package com.example.todolist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.data.PRIORITY
import com.example.todolist.data.Quick
import com.example.todolist.holder.QuickItemViewHolder

class QuickItemAdapter(private val data: List<Quick>, private val listener: OnItemClickListener) :
	RecyclerView.Adapter<QuickItemViewHolder>() {
	interface OnItemClickListener {
		fun onItemClick(item: Quick, position: Int)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuickItemViewHolder {
		val itemView =
			LayoutInflater.from(parent.context).inflate(R.layout.quick_item, parent, false)
		return QuickItemViewHolder(itemView)
	}

	override fun onBindViewHolder(holder: QuickItemViewHolder, position: Int) {
		holder.title.text = data[position].title
		holder.description.text = data[position].description
		holder.priority.setImageResource(
			when (data[position].priority) {
				PRIORITY.LOW -> R.drawable.ic_low_priority
				PRIORITY.MEDIUM -> R.drawable.ic_medium_priority
				PRIORITY.HIGH -> R.drawable.ic_high_priority
			}
		)
		holder.itemView.setOnClickListener {
			listener.onItemClick(data[position], position)
		}
	}

	override fun getItemCount(): Int = data.size
}

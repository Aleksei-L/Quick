package com.example.todolist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.data.PRIORITY
import com.example.todolist.data.Quick
import com.example.todolist.holder.QuickItemViewHolder


class QuickItemAdapter(private val data: List<Quick>?, private val listener: OnItemClickListener) :
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
		holder.title.text = data?.get(position)?.title ?: ""
		holder.description.text = data?.get(position)?.description ?: ""
		if (data != null && data[position].priority == PRIORITY.LOW) {
			holder.priority.setImageResource(R.drawable.ic_priority_low)
			val color = holder.itemView.context.resources.getColor(R.color.grey, null)
			holder.title.setTextColor(color)
			holder.description.setTextColor(color)
		} else if (data != null && data[position].priority == PRIORITY.HIGH)
			holder.priority.setImageResource(R.drawable.ic_priority_high)
		holder.itemView.setOnClickListener {
			listener.onItemClick(data?.get(position) ?: Quick(0, "", "", PRIORITY.MEDIUM), position)
		}
	}

	override fun getItemCount(): Int = data?.size ?: 0
}

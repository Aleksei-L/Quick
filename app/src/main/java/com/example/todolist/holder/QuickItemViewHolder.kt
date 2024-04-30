package com.example.todolist.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R

class QuickItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
	val title: TextView = itemView.findViewById(R.id.item_title)
	val description: TextView = itemView.findViewById(R.id.item_description)
	val priority: ImageView = itemView.findViewById(R.id.priority)
}

package com.example.todolist.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R

class QuickItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
	val title = itemView.findViewById<TextView>(R.id.item_title)
	val description = itemView.findViewById<TextView>(R.id.item_description)
	val priority = itemView.findViewById<ImageView>(R.id.priority)
}

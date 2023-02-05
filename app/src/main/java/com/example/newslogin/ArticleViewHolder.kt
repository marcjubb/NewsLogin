package com.example.newslogin

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleTextView: TextView = itemView.findViewById(R.id.title)
    val descriptionTextView: TextView = itemView.findViewById(R.id.description)
}
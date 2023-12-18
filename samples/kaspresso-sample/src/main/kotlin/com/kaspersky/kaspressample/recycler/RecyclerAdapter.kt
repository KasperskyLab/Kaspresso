package com.kaspersky.kaspressample.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kaspersky.kaspressample.R

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun getItemCount() = ITEMS_COUNT

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) = holder.bind(position)

    class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView: TextView by lazy { view.findViewById(R.id.textView) }

        fun bind(position: Int) {
            textView.text = position.toString()
        }
    }

    companion object {
        private const val ITEMS_COUNT = 30
    }
}

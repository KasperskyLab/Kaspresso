package com.kaspersky.kaspressample

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_recycler_stub.*

class RecyclerStubActivity : AppCompatActivity() {

    val data = Array(100) { it.toString() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_stub)

        recycler_stub.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@RecyclerStubActivity)

            adapter = object : RecyclerView.Adapter<StubViewHolder>() {

                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StubViewHolder =
                    StubViewHolder(
                        LayoutInflater.from(parent.context).inflate(R.layout.list_stub_item, parent, false)
                    )

                override fun onBindViewHolder(viewHolder: StubViewHolder, position: Int) =
                    viewHolder.bind(position, data[position])

                override fun getItemCount(): Int = data.size
            }
        }
    }

    private class StubViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.title)

        fun bind(position: Int, value: String) {
            title.text = value
            title.setBackgroundColor(if (position % 2 == 0) Color.WHITE else Color.GRAY)
        }
    }
}
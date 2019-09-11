package com.kaspersky.kaspressample

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_list_stub.*
import kotlinx.android.synthetic.main.list_stub_item.view.*

class ListStubActivity : AppCompatActivity() {

    private companion object {
        private const val ITEMS_AMOUNT = 100
    }

    private val data = Array(ITEMS_AMOUNT) { it.toString() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_stub)

        list_stub.apply {
            adapter = object : BaseAdapter() {

                override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
                    val view: View

                    val vh =  if (convertView != null) {
                        view = convertView
                        convertView.tag as StubViewHolder
                    } else {
                        view = layoutInflater.inflate(R.layout.list_stub_item, null)
                        StubViewHolder(view.findViewById(R.id.title)).apply { view.tag = this }
                    }

                    vh.apply {
                        title.text = data[position]
                        title.setBackgroundColor(if (position % 2 == 0) Color.WHITE else Color.GRAY)
                    }

                    return view
                }

                override fun getItemId(position: Int) = position.toLong()

                override fun getItem(position: Int) = data[position]

                override fun getCount() = data.size
            }
        }
    }

    private class StubViewHolder(val title: TextView)
}
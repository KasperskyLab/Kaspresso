package com.kaspersky.kaspressample.recycler

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kaspersky.kaspressample.R

class RecyclerFragment : Fragment(R.layout.fragment_recycler) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.recycler).run {
            layoutManager = LinearLayoutManager(view.context)
            adapter = RecyclerAdapter()
        }
    }
}

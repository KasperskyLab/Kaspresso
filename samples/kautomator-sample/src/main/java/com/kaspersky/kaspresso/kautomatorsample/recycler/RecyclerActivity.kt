package com.kaspersky.kaspresso.kautomatorsample.recycler

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kaspersky.kaspresso.kautomatorsample.R
import com.kaspersky.kaspresso.kautomatorsample.databinding.ActivityRecyclerBinding
import com.kaspersky.kaspresso.kautomatorsample.model.SimpleModel

class RecyclerActivity : AppCompatActivity() {

    companion object {
        private const val ITEM_COUNT = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        val binding = ActivityRecyclerBinding.inflate(layoutInflater)
        binding.recycler.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@RecyclerActivity)
            adapter = RecyclerAdapter(generateItems())
        }

        setContentView(binding.root)
    }

    private fun generateItems(): List<SimpleModel> {
        return (0..ITEM_COUNT).map { SimpleModel.randomizeNewItem() }.toMutableList().apply {
            SimpleModel.richWithLabels(this)
        }
    }
}

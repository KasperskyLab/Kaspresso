package com.kaspersky.kaspresso.sample_kautomator.recycler

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kaspersky.kaspresso.sample_kautomator.R
import com.kaspersky.kaspresso.sample_kautomator.model.SimpleModel
import kotlinx.android.synthetic.main.activity_recycler.*

class RecyclerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        recycler.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@RecyclerActivity)
            adapter = RecyclerAdapter(generateItems())
        }
    }

    private fun generateItems(): List<SimpleModel> {
        return (1..100).map { SimpleModel.randomizeNewItem() }.toMutableList().apply {
            add(0, SimpleModel("Beginning", 0))
            add(50,
                SimpleModel("Scroll to me", 1)
            )
            add(SimpleModel("End", 2))
        }
    }
}
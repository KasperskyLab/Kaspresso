package com.kaspersky.kaspresso.tutorial.lists2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.kaspersky.kaspresso.tutorial.databinding.ActivityNoteListBinding
import com.kaspersky.kaspresso.tutorial.lists2.list.ExampleAdapter
import kotlinx.coroutines.launch

class ExampleListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteListBinding

    private val viewModel by lazy {
        ViewModelProvider(this)[ExampleListViewModel::class.java]
    }

    private val exampleAdapter = ExampleAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvNotes.adapter = exampleAdapter

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    exampleAdapter.submitList(state.list)
                }
            }
        }

        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT,
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = exampleAdapter.getItemByIndexOrNull(viewHolder.adapterPosition)

                //ItemTouchHelper.LEFT

                when (item) {
                    is ExampleView.ListModel.Action -> {
                        viewModel.openMenu(item)
                        exampleAdapter.notifyItemChanged(viewHolder.adapterPosition)
                    }

                    is ExampleView.ListModel.Notes -> viewModel.remove(item)
                    null -> exampleAdapter.notifyItemChanged(viewHolder.adapterPosition)
                }

//                if (direction == ItemTouchHelper.LEFT){
//                    Log.e("TEB","left Swipe")
//                }else {
//                    Log.e("TEB","Right Swipe")
//                }
                //exampleAdapter.notifyItemChanged(viewHolder.adapterPosition)
            }
        }
        ItemTouchHelper(itemTouchCallback).attachToRecyclerView(binding.rvNotes)
    }
}

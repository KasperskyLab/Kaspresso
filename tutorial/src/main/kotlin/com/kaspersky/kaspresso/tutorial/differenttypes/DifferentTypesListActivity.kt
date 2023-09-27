package com.kaspersky.kaspresso.tutorial.differenttypes

import android.graphics.Canvas
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.kaspersky.kaspresso.tutorial.databinding.ActivityDifferentTypesListBinding
import com.kaspersky.kaspresso.tutorial.differenttypes.list.DifferentTypesAdapter
import kotlinx.coroutines.launch

class DifferentTypesListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDifferentTypesListBinding
    private val viewModel: DifferentTypesListViewModel by viewModels()
    private val differentTypesAdapter = DifferentTypesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDifferentTypesListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvNotes.adapter = differentTypesAdapter

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    differentTypesAdapter.submitList(state.list)
                }
            }
        }

//        val itemTouchCallback = object : ItemTouchHelper.Callback() {
//            override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
//                val swipeFlags = ItemTouchHelper.START and ItemTouchHelper.END
//                return makeMovementFlags(0, swipeFlags)
//            }
//
//            override fun onMove(
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder,
//                target: RecyclerView.ViewHolder,
//            ): Boolean {
//                return false
//            }
//
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                val item = exampleAdapter.getItemByIndexOrNull(viewHolder.adapterPosition)
//
//                //ItemTouchHelper.LEFT
//
//                when (item) {
//                    is ExampleView.ListModel.Action -> {
//                        viewModel.openMenu(item)
//                        exampleAdapter.notifyItemChanged(viewHolder.adapterPosition)
//                    }
//
//                    is ExampleView.ListModel.Notes -> viewModel.remove(item)
//                    null -> exampleAdapter.notifyItemChanged(viewHolder.adapterPosition)
//                }
//            }
//        }

        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT,
        ) {
            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = differentTypesAdapter.getItemByIndexOrNull(viewHolder.adapterPosition)

                //ItemTouchHelper.LEFT

                when (item) {
                    is DifferentTypesListView.ListModel.Action -> {
                        viewModel.openMenu(item)
                        differentTypesAdapter.notifyItemChanged(viewHolder.adapterPosition)
                    }

                    is DifferentTypesListView.ListModel.Notes -> viewModel.remove(item)
                    null -> differentTypesAdapter.notifyItemChanged(viewHolder.adapterPosition)
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

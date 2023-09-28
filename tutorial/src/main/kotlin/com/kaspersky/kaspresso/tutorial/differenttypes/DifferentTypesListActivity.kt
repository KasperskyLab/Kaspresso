package com.kaspersky.kaspresso.tutorial.differenttypes

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.kaspersky.kaspresso.tutorial.databinding.ActivityDifferentTypesListBinding
import com.kaspersky.kaspresso.tutorial.differenttypes.list.DifferentTypesAdapter
import kotlinx.coroutines.launch

class DifferentTypesListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDifferentTypesListBinding
    private val viewModel: DifferentTypesListViewModel by viewModels()
    private val differentTypesAdapter by lazy {
        DifferentTypesAdapter(
            onRemove = {
                viewModel.remove(it)
            }
        )
    }

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
    }
}

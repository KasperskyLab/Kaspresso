package com.kaspersky.kaspresso.tutorial.differenttypes

import androidx.lifecycle.ViewModel
import com.kaspersky.kaspresso.tutorial.differenttypes.DifferentTypesListView.UiState
import com.kaspersky.kaspresso.tutorial.lists.Note
import com.kaspersky.kaspresso.tutorial.lists.Priority
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

internal class DifferentTypesListViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    internal val uiState: StateFlow<UiState> = _uiState

    init {
        _uiState.update { state ->
            val list = mutableListOf<DifferentTypesListView.ListModel>().apply {
                val priorities = Priority.values()
                repeat(10) { n ->
                    val priority = priorities[n % priorities.size]
                    val note = Note(id = n, text = "Note number $n", priority = priority)
                    val item = if (n < 5) {
                        DifferentTypesListView.ListModel.Notes(note)
                    } else {
                        DifferentTypesListView.ListModel.Action(note)
                    }
                    add(item)
                }
            }
            state.copy(list = list)
        }
    }

    fun remove(item: DifferentTypesListView.ListModel?) {
        _uiState.update { state ->
            val list = state.list.filter { model ->
                when {
                    model is DifferentTypesListView.ListModel.Action
                            && item is DifferentTypesListView.ListModel.Action -> model.note.id != item.note.id

                    model is DifferentTypesListView.ListModel.Notes
                            && item is DifferentTypesListView.ListModel.Notes -> model.note.id != item.note.id

                    else -> true
                }
            }
            state.copy(list = list)
        }
    }
}

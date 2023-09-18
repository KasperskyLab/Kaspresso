package com.kaspersky.kaspresso.tutorial.lists2

import androidx.lifecycle.ViewModel
import com.kaspersky.kaspresso.tutorial.lists.Note
import com.kaspersky.kaspresso.tutorial.lists.Priority
import com.kaspersky.kaspresso.tutorial.lists2.ExampleView.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

internal class ExampleListViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    internal val uiState: StateFlow<UiState> = _uiState

    init {
        _uiState.update { state ->
            val list = mutableListOf<ExampleView.ListModel>().apply {
                val priorities = Priority.values()
                repeat(10) { n ->
                    val priority = priorities[n % priorities.size]
                    val note = Note(id = n, text = "Note number $n", priority = priority)
                    val item = if (n < 5) {
                        ExampleView.ListModel.Notes(note)
                    } else {
                        ExampleView.ListModel.Action(note, false)
                    }
                    add(item)
                }
            }
            state.copy(list = list)
        }
    }

    fun remove(item: ExampleView.ListModel?) {
        _uiState.update { state ->
            val list = state.list.filter {
                when {
                    it is ExampleView.ListModel.Action
                            && item is ExampleView.ListModel.Action -> it.note.id != item.note.id

                    it is ExampleView.ListModel.Notes
                            && item is ExampleView.ListModel.Notes -> it.note.id != item.note.id

                    else -> false
                }
            }
            state.copy(list = list)
        }
    }

    fun openMenu(item: ExampleView.ListModel.Action) {
        _uiState.update { state ->
            val list = state.list.map {
                if (it is ExampleView.ListModel.Action
                    && it.note.id == item.note.id
                ) it.copy(isOpen = true)
                else it
            }
            state.copy(list = list)
        }
    }
}

package com.kaspersky.kaspresso.tutorial.lists2

import com.kaspersky.kaspresso.tutorial.lists.Note

internal interface ExampleView {

    data class UiState(
        val list: List<ListModel> = emptyList(),
    )

    sealed interface ListModel {
        data class Notes(val note: Note) : ListModel
        data class Action(
            val note: Note,
            val isOpen: Boolean,
        ) : ListModel
    }
}

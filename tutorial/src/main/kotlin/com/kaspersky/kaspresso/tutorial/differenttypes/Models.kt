package com.kaspersky.kaspresso.tutorial.differenttypes

import com.kaspersky.kaspresso.tutorial.lists.Note

internal interface DifferentTypesListView {

    data class UiState(
        val list: List<ListModel> = emptyList(),
    )

    sealed interface ListModel {
        data class Notes(val note: Note) : ListModel
        data class Action(val note: Note) : ListModel
    }
}

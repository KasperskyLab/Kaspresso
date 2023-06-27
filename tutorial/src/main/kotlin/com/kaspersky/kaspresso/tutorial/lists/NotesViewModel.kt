package com.kaspersky.kaspresso.tutorial.lists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotesViewModel : ViewModel() {

    private val initialList = mutableListOf<Note>().apply {
        val priorities = Priority.values()
        repeat(COUNT_OF_NOTES) { id ->
            val priority = priorities[id % priorities.size]
            val note = Note(id = id, text = "Note number $id", priority = priority)
            add(note)
        }
    }

    private val _notes = MutableLiveData<List<Note>>(initialList)
    val notes: LiveData<List<Note>> = _notes

    fun remove(note: Note) {
        val oldList = notes.value ?: return
        val newList = oldList.toMutableList().apply {
            remove(note)
        }
        _notes.value = newList
    }

    companion object {

        private const val COUNT_OF_NOTES = 3
    }
}

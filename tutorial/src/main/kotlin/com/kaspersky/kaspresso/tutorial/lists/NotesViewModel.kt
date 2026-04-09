package com.kaspersky.kaspresso.tutorial.lists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

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

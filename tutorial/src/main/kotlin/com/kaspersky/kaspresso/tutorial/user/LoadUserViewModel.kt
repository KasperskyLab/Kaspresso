package com.kaspersky.kaspresso.tutorial.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

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

class LoadUserViewModel : ViewModel() {

    private val repository = LoadUserRepository

    private val _state = MutableStateFlow<State>(State.Initial)
    val state = _state.asStateFlow()

    fun loadUser() {
        viewModelScope.launch {
            _state.value = State.Progress
            try {
                val user = repository.loadUser()
                _state.value = State.Content(user)
            } catch (e: Exception) {
                _state.value = State.Error
            }
        }
    }
}

sealed class State {

    data class Content(val user: User) : State()

    object Progress : State()

    object Error : State()

    object Initial : State()
}

package com.kaspersky.kaspresso.tutorial.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

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

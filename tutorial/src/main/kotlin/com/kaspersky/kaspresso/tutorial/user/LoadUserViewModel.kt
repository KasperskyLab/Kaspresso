package com.kaspersky.kaspresso.tutorial.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoadUserViewModel : ViewModel() {

    private val repository = LoadUserRepository

    private val _state = MutableStateFlow<State>(State.Initial)
    val state = _state.asStateFlow()

    private val loadingExceptionHandler = CoroutineExceptionHandler { _, _ ->
        _state.value = State.Error
    }

    fun loadUser() {
        _state.value = State.Loading

        viewModelScope.launch(loadingExceptionHandler) {
            val user = repository.loadUser()
            _state.value = State.Content(user)
        }
    }
}

sealed class State {

    data class Content(val user: User) : State()

    object Loading : State()

    object Error : State()

    object Initial : State()
}

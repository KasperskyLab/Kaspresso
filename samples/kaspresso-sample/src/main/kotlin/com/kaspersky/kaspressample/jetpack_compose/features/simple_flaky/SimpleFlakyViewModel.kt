package com.kaspersky.kaspressample.jetpack_compose.features.simple_flaky

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SimpleFlakyViewModel : ViewModel() {

    private val _mutableSimpleFlakyState = MutableLiveData(
        SimpleFlakyState(
            firstButtonVisibility = true,
            secondButtonVisibility = false,
            editTextVisibility = false,
            editText = "Some text"
        )
    )

    val simpleFlakyStateLiveData: LiveData<SimpleFlakyState> = _mutableSimpleFlakyState

    fun firstButtonClick() {
        viewModelScope.launch {
            delay(3_000)
            _mutableSimpleFlakyState.value = _mutableSimpleFlakyState.value?.copy(secondButtonVisibility = true)
        }
    }

    fun secondButtonClick() {
        viewModelScope.launch {
            delay(3_000)
            _mutableSimpleFlakyState.value = _mutableSimpleFlakyState.value?.copy(editTextVisibility = true)
        }
    }

    fun editTextChange(text: String) {
        _mutableSimpleFlakyState.value = _mutableSimpleFlakyState.value?.copy(editText = text)
    }
}

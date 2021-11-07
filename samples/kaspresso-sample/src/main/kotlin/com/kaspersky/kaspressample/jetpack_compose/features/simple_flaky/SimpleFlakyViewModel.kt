package com.kaspersky.kaspressample.jetpack_compose.features.simple_flaky

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class SimpleFlakyViewModel : ViewModel() {

    // interesting problem on the JVM environment
    // default viewModelScope doesn't work correctly
    // quick research has not showed the possible reason
    // we think it will be fixed on later versions of related libraries
    private val customScope = viewModelScope + Dispatchers.IO

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
        customScope.launch {
            delay(3000L)
            _mutableSimpleFlakyState.postValue(_mutableSimpleFlakyState.value?.copy(secondButtonVisibility = true))
        }
    }

    fun secondButtonClick() {
        customScope.launch {
            delay(3_000)
            _mutableSimpleFlakyState.postValue(_mutableSimpleFlakyState.value?.copy(editTextVisibility = true))
        }
    }

    fun editTextChange(text: String) {
        _mutableSimpleFlakyState.value = _mutableSimpleFlakyState.value?.copy(editText = text)
    }
}

package com.kaspersky.kaspresso.composesupport.sample.features.flaky

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

private const val TIMEOUT = 1_000L

class SimpleFlakyViewModel : ViewModel() {

    // interesting problem on the JVM environment
    // default viewModelScope doesn't work correctly
    // quick research has not showed the possible reason
    // we think it will be fixed on later versions of related libraries
    private val customScope = viewModelScope + Dispatchers.IO

    private val _simpleFlakyStateLiveData = MutableLiveData(
        SimpleFlakyState(
            firstButtonVisibility = true,
            secondButtonVisibility = false,
            editTextVisibility = false,
            editText = "Some text"
        )
    )

    val simpleFlakyStateLiveData: LiveData<SimpleFlakyState> = _simpleFlakyStateLiveData

    fun firstButtonClick() {
        customScope.launch {
            delay(TIMEOUT)
            _simpleFlakyStateLiveData.postValue(_simpleFlakyStateLiveData.value?.copy(secondButtonVisibility = true))
        }
    }

    fun secondButtonClick() {
        customScope.launch {
            delay(TIMEOUT)
            _simpleFlakyStateLiveData.postValue(_simpleFlakyStateLiveData.value?.copy(editTextVisibility = true))
        }
    }

    fun editTextChange(text: String) {
        _simpleFlakyStateLiveData.value = _simpleFlakyStateLiveData.value?.copy(editText = text)
    }
}

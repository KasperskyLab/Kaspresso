package com.kaspersky.kaspresso.composesupport.sample.features.sanityflaky

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

private const val TIMEOUT = 3_000L

class SanityFlakyViewModel : ViewModel() {

    // interesting problem on the JVM environment
    // default viewModelScope doesn't work correctly
    // quick research has not showed the possible reason
    // we think it will be fixed on later versions of related libraries
    private val customScope = viewModelScope + Dispatchers.IO

    private val _sanityFlakyStateLiveData = MutableLiveData(
        SanityFlakyState(
            firstButtonVisibility = true,
            secondButtonVisibility = false
        )
    )

    val sanityFlakyStateLiveData: LiveData<SanityFlakyState> = _sanityFlakyStateLiveData

    fun firstButtonClick() {
        customScope.launch {
            delay(TIMEOUT)
            _sanityFlakyStateLiveData.postValue(_sanityFlakyStateLiveData.value?.copy(secondButtonVisibility = true))
        }
    }
}

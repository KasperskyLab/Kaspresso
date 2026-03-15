package com.kaspersky.kaspresso.composesupport.sample.features.flaky

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

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

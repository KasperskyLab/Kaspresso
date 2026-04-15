package com.kaspersky.kaspressample.docloc

import android.graphics.Color

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

class ScreenshotSamplePresenter(private val view: ScreenshotSampleView) {
    var value = 0

    fun init() {
        view.setCounterValue(value)
        view.setBackgroundColor(Color.WHITE)
    }

    fun increment() {
        view.setCounterValue(++value)
    }

    fun decrement() {
        view.setCounterValue(--value)
    }

    fun setBackgroundColor(color: Int) {
        view.setBackgroundColor(color)
    }
}

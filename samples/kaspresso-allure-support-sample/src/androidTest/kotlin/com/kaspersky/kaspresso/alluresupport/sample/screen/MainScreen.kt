package com.kaspersky.kaspresso.alluresupport.sample.screen

import com.kaspersky.kaspresso.alluresupport.sample.MainActivity
import com.kaspersky.kaspresso.alluresupport.sample.R
import com.kaspersky.kaspresso.screens.KScreen
import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView

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

object MainScreen : KScreen<MainScreen>() {

    override val layoutId: Int = R.layout.activity_main

    override val viewClass: Class<*> = MainActivity::class.java

    val incrementButton = KButton { withId(R.id.increment) }
    val decrementButton = KButton { withId(R.id.decrement) }
    val clearButton = KButton { withId(R.id.clear) }
    val valueText = KTextView { withId(R.id.value) }

    fun BaseTestContext.assertValue(value: Int) {
        valueText.hasText(device.targetContext.getString(R.string.value_placeholder, value))
    }
}

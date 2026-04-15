package com.kaspersky.kaspresso.kautomatorsample.screen

import com.kaspersky.components.kautomator.component.check.UiCheckBox
import com.kaspersky.components.kautomator.component.common.views.UiView
import com.kaspersky.components.kautomator.component.edit.UiEditText
import com.kaspersky.components.kautomator.component.text.UiButton
import com.kaspersky.components.kautomator.component.text.UiTextView
import com.kaspersky.components.kautomator.screen.UiScreen
import com.kaspersky.kaspresso.kautomatorsample.R

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

object MainNativeScreen : UiScreen<MainNativeScreen>() {

    override val packageName: String = "com.kaspersky.kaspresso.kautomatorsample"

    val header = UiTextView { withText(R.string.main_activity_text) }
    val subHeader = UiTextView { textStartsWith(R.string.main_activity_text_start) }
    val image = UiView { withContentDescription(R.string.main_activity_image_description) }
    val simpleEditText = UiEditText { withId(R.id.editText) }
    val simpleButton = UiButton { withId(R.id.button) }
    val checkBox = UiCheckBox { withId(R.id.checkBox) }
}

package com.kaspersky.kaspressample.external_screens

import com.kaspersky.components.kautomator.component.check.UiCheckBox
import com.kaspersky.components.kautomator.component.edit.UiEditText
import com.kaspersky.components.kautomator.component.text.UiButton
import com.kaspersky.components.kautomator.component.text.UiTextView
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.measure.MeasureActivity

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

object UiMeasureScreen : UiSampleScreen<UiMeasureScreen>() {

    override val layoutId: Int? = R.layout.activity_measure
    override val viewClass: Class<*>? = MeasureActivity::class.java

    val button1 = UiButton { withId(this@UiMeasureScreen.packageName, "button_1") }

    val button2 = UiButton { withId(this@UiMeasureScreen.packageName, "button_2") }

    val textView = UiTextView { withId(this@UiMeasureScreen.packageName, "textview") }

    val edit = UiEditText { withId(this@UiMeasureScreen.packageName, "edit") }

    val checkBox = UiCheckBox { withId(this@UiMeasureScreen.packageName, "checkBox") }
}

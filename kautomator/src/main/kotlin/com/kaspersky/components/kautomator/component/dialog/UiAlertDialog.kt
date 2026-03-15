@file:Suppress("unused")
package com.kaspersky.components.kautomator.component.dialog

import com.kaspersky.components.kautomator.component.common.views.UiBaseView
import com.kaspersky.components.kautomator.component.text.UiButton
import com.kaspersky.components.kautomator.component.text.UiTextView

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

/**
 * View for acting and asserting on default AlertDialog
 */
class UiAlertDialog(private val packageName: String) : UiBaseView<UiAlertDialog>({ withPackage(packageName) }) {

    companion object {
        private const val TITLE_ID = "alertTitle"
        private const val MESSAGE_ID = "android:id/message"
        private const val POSITIVE_BTN_ID = "android:id/button1"
        private const val NEGATIVE_BUTTON_ID = "android:id/button2"
        private const val NEUTRAL_BUTTON_ID = "android:id/button3"
    }

    val positiveButton = UiButton { withResourceName(POSITIVE_BTN_ID) }
    val negativeButton = UiButton { withResourceName(NEGATIVE_BUTTON_ID) }
    val neutralButton = UiButton { withResourceName(NEUTRAL_BUTTON_ID) }
    val title = UiTextView { withId(this@UiAlertDialog.packageName, TITLE_ID) }
    val message = UiTextView { withResourceName(MESSAGE_ID) }
}

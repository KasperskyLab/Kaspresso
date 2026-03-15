package com.kaspersky.kaspressample.external_screens

import androidx.appcompat.app.AlertDialog
import com.kaspersky.components.kautomator.component.text.UiTextView
import com.kaspersky.kaspressample.R

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

object UiContinuouslyDialogScreen : UiSampleScreen<UiContinuouslyDialogScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = AlertDialog::class.java

    val dialogTitle = UiTextView {
        withText(this@UiContinuouslyDialogScreen.context.getString(R.string.continuously_dialog_title))
    }
}

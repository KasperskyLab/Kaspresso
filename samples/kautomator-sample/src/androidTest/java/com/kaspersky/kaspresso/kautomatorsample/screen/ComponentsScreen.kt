package com.kaspersky.kaspresso.kautomatorsample.screen

import com.kaspersky.components.kautomator.component.bottomnav.UiBottomNavigationView
import com.kaspersky.components.kautomator.component.check.UiCheckBox
import com.kaspersky.components.kautomator.component.chip.UiChipGroup
import com.kaspersky.components.kautomator.component.dialog.UiAlertDialog
import com.kaspersky.components.kautomator.component.edit.UiEditText
import com.kaspersky.components.kautomator.component.text.UiButton
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

object ComponentsScreen : UiScreen<ComponentsScreen>() {

    override val packageName: String = "com.kaspersky.kaspresso.kautomatorsample"

    val bottomNav = UiBottomNavigationView { withId(this@ComponentsScreen.packageName, "bottomNav") }
    val checkbox = UiCheckBox { withId(this@ComponentsScreen.packageName, "checkBox") }
    val chipGroup = UiChipGroup { withId(this@ComponentsScreen.packageName, "chipGroup") }
    val showDialogBtn = UiButton { withId(this@ComponentsScreen.packageName, "showDialogBtn") }
    val dialog = UiAlertDialog(packageName)

    val editTextByHintResId = UiEditText { withHint(R.string.hint) }
    val editTextByHintText = UiEditText { withHint("Some hint") }
    val editTextByHintPattern = UiEditText { withHint("Some.*".toPattern()) }
}

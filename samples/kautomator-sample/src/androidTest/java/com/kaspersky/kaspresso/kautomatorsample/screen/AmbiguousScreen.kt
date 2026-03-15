package com.kaspersky.kaspresso.kautomatorsample.screen

import com.kaspersky.components.kautomator.component.text.UiButton
import com.kaspersky.components.kautomator.screen.UiScreen
import java.util.regex.Pattern

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

private const val PATTERN = ".*Ambiguous button.*"

object AmbiguousScreen : UiScreen<AmbiguousScreen>() {

    override val packageName: String = "com.kaspersky.kaspresso.kautomatorsample"

    val anyPossibleAmbiguousButtonByIdAndText = UiButton {
        withId(this@AmbiguousScreen.packageName, "ambiguous_button")
        withText(Pattern.compile(PATTERN, Pattern.CASE_INSENSITIVE))
        or {
            withId(this@AmbiguousScreen.packageName, "ambiguous_redesigned_button")
            withText(Pattern.compile(PATTERN, Pattern.CASE_INSENSITIVE))
        }
        or {
            withId(this@AmbiguousScreen.packageName, "ambiguous_new_button")
            withText(Pattern.compile(PATTERN, Pattern.CASE_INSENSITIVE))
        }
        or {
            withId(this@AmbiguousScreen.packageName, "ambiguous_vendor_button")
            withText(Pattern.compile(PATTERN, Pattern.CASE_INSENSITIVE))
        }
    }
}

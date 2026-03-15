package com.kaspersky.kaspressample.external_screens

import com.kaspersky.components.kautomator.component.scroll.UiScrollView
import com.kaspersky.components.kautomator.component.text.UiButton
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.flaky.CommonFlakyActivity

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

object UiCommonFlakyScreen : UiSampleScreen<UiCommonFlakyScreen>() {

    override val layoutId: Int? = R.layout.activity_common_flaky
    override val viewClass: Class<*>? = CommonFlakyActivity::class.java

    val scrollView = UiScrollView {
        withId(this@UiCommonFlakyScreen.packageName, "scroll_view")
    }

    val btn1 = UiButton {
        withId(this@UiCommonFlakyScreen.packageName, "scroll_view_btn1")
    }

    val btn3 = UiButton {
        withId(this@UiCommonFlakyScreen.packageName, "scroll_view_btn3")
    }

    val btn5 = UiButton {
        withId(this@UiCommonFlakyScreen.packageName, "scroll_view_btn5")
    }

    val tv6 = UiButton {
        withId(this@UiCommonFlakyScreen.packageName, "scroll_view_tv6")
    }
}

package com.kaspersky.kaspressample.external_screens

import com.kaspersky.components.kautomator.component.text.UiButton
import com.kaspersky.kaspressample.MainActivity
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

object UiMainScreen : UiSampleScreen<UiMainScreen>() {

    override val layoutId: Int? = R.layout.activity_main
    override val viewClass: Class<*>? = MainActivity::class.java

    val simpleButton = UiButton { withId(this@UiMainScreen.packageName, "activity_main_simple_sample_button") }

    val flakyButton = UiButton { withId(this@UiMainScreen.packageName, "activity_main_flaky_sample_button") }

    val continuouslyButton = UiButton { withId(this@UiMainScreen.packageName, "activity_main_continuously_sample_button") }

    val composeButton = UiButton { withId(this@UiMainScreen.packageName, "activity_main_complex_compose_sample_button") }

    val idleWaitingButton = UiButton { withId(this@UiMainScreen.packageName, "activity_main_idlewaiting_sample_button") }

    val measureButton = UiButton { withId(this@UiMainScreen.packageName, "activity_main_measure_sample_button") }
}

package com.kaspersky.kaspressample.screen

import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.compose.ComplexComposeSampleActivity
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.text.KButton

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

object ComplexComposeScreen : KScreen<ComplexComposeScreen>() {

    override val layoutId: Int = R.layout.activity_complex_compose
    override val viewClass: Class<*> = ComplexComposeSampleActivity::class.java

    val startButton = KButton { withId(R.id.activity_compose_start) }
    val stage1Button = KButton { withId(R.id.activity_compose_stage_1) }
    val stage2Button = KButton { withId(R.id.activity_compose_stage_2) }
    val finishButton = KButton { withId(R.id.activity_compose_finish) }
}

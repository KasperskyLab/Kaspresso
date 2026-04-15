package com.kaspersky.kaspressample.screen

import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspresso.screens.KScreen
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

    val autoScrollScrollViewWithPaddingButton = KButton { withId(R.id.activity_main_auto_scroll_scrollView_with_padding_button) }

    val simpleButton = KButton { withId(R.id.activity_main_simple_sample_button) }

    val webViewButton = KButton { withId(R.id.activity_main_webview_sample_button) }

    val flakyButton = KButton { withId(R.id.activity_main_flaky_sample_button) }

    val continuouslyButton = KButton { withId(R.id.activity_main_continuously_sample_button) }

    val composeButton = KButton { withId(R.id.activity_main_complex_compose_sample_button) }

    val idleWaitingButton = KButton { withId(R.id.activity_main_idlewaiting_sample_button) }

    val systemDialogsButton = KButton { withId(R.id.activity_main_system_dialogs_button) }

    val changeLocaleButton = KButton { withId(R.id.activity_main_change_locale_mid_test_button) }

    val withToolbarButton = KButton { withId(R.id.activity_with_toolbar_button) }

    val descriptionText = KTextView { withId(R.id.activity_main_title) }
}

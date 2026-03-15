package com.kaspersky.kaspresso.composesupport.sample.screen

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import com.kaspersky.components.composesupport.core.KNode
import com.kaspersky.kaspresso.composesupport.sample.resources.C
import io.github.kakaocup.compose.node.element.ComposeScreen

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

class ComposeSanityFlakyScreen(semanticsProvider: SemanticsNodeInteractionsProvider) :
    ComposeScreen<ComposeSanityFlakyScreen>(
        semanticsProvider = semanticsProvider,
        viewBuilderAction = { hasTestTag(C.Tag.sanity_flaky_screen_container) }
    ) {

    val firstButton: KNode = child {
        hasTestTag(C.Tag.sanity_flaky_screen_simple_first_button)
    }

    val secondButton: KNode = child {
        hasTestTag(C.Tag.sanity_flaky_screen_simple_second_button)
    }
}

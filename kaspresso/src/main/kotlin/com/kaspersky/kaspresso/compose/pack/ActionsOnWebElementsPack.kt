package com.kaspersky.kaspresso.compose.pack

import androidx.test.espresso.web.webdriver.Locator
import io.github.kakaocup.kakao.web.WebElementBuilder
import com.kaspersky.kaspresso.compose.pack.branch.ComplexComposeBranch
import com.kaspersky.kaspresso.compose.pack.branch.ComplexComposeBranchBuilder
import kotlin.properties.Delegates

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
 * The builder class for parameters of [com.kaspersky.kaspresso.compose.WebComposeProvider.compose] method.
 */
class ActionsOnWebElementsPack(
    private val webElementBuilder: WebElementBuilder
) {
    private val complexComposeBranchBuilders: MutableList<ComplexComposeBranchBuilder<WebElementBuilder.KWebInteraction>> = mutableListOf()

    /**
     * Builds the lambda to add to [actions] that invokes the given [action] on the web element built by
     * [webElementBuilder] with given [locator] and [value].
     *
     * @param locator the locator type of web view element.
     * @param value the value to be searched for in web view.
     * @param action actions or assertions on the interacted view.
     */
    fun orWithElement(locator: Locator, value: String, action: WebElementBuilder.KWebInteraction.() -> Unit): ComplexComposeBranchBuilder<WebElementBuilder.KWebInteraction> {
        var complexComposeBranchBuilder by Delegates.notNull<ComplexComposeBranchBuilder<WebElementBuilder.KWebInteraction>>()
        webElementBuilder.withElement(locator, value) {
            complexComposeBranchBuilder = ComplexComposeBranchBuilder(this, { action.invoke(this) })
                .also { complexComposeBranchBuilders += it }
        }
        return complexComposeBranchBuilder
    }

    /**
     * @return the built parameters for [com.kaspersky.kaspresso.compose.WebComposeProvider.compose] method.
     */
    internal fun build(): List<ComplexComposeBranch<WebElementBuilder.KWebInteraction>> {
        require(complexComposeBranchBuilders.isNotEmpty()) { "Nothing to compose" }

        val composeBranches = mutableListOf<ComplexComposeBranch<WebElementBuilder.KWebInteraction>>()
        complexComposeBranchBuilders.forEach {
            composeBranches += it.build()
        }

        return composeBranches
    }
}

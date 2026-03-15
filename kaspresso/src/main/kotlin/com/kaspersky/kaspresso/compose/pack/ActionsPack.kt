package com.kaspersky.kaspresso.compose.pack

import com.kaspersky.kaspresso.compose.pack.branch.ComplexComposeBranch
import com.kaspersky.kaspresso.compose.pack.branch.ComplexComposeBranchBuilder

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
 * The builder class for parameters of [com.kaspersky.kaspresso.compose.ComposeProvider.compose] and
 * [com.kaspersky.kaspresso.compose.WebComposeProvider.compose]] methods.
 */
class ActionsPack<T>(
    private val element: T
) {

    private val complexComposeBranchBuilders: MutableList<ComplexComposeBranchBuilder<out T>> = mutableListOf()

    /**
     * Builds the lambda to add to [actions] that invokes the given [action] on the interacted view of type [T].
     *
     * @param action actions or assertions on the interacted view.
     */
    fun or(action: T.() -> Unit): ComplexComposeBranchBuilder<T> {
        return ComplexComposeBranchBuilder(element, { action.invoke(element) })
            .also { complexComposeBranchBuilders += it }
    }

    /**
     * @return the built parameters for [com.kaspersky.kaspresso.compose.ComposeProvider.compose] method.
     */
    internal fun build(): List<ComplexComposeBranch<out T>> {
        require(complexComposeBranchBuilders.isNotEmpty()) { "Nothing to compose" }

        val composeBranches = mutableListOf<ComplexComposeBranch<out T>>()
        complexComposeBranchBuilders.forEach {
            composeBranches += it.build()
        }

        return composeBranches
    }
}

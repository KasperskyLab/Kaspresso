package com.kaspersky.kaspresso.compose.pack.branch

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
 * Builder of ComplexComposeBranch's using in compose
 */
class ComplexComposeBranchBuilder<ElementType>(
    private val element: ElementType,
    private val check: () -> Unit
) {

    private var postAction: (() -> Unit)? = null

    infix fun then(postAction: () -> Unit) {
        if (this.postAction == null) {
            this.postAction = postAction
            return
        }
        throw ComposeBuilderException(
            "Please, use compose functionality correctly! " +
                    "Keep the rule: one `or` <=> one `then`!"
        )
    }

    infix fun thenContinue(postAction: ElementType.() -> Unit) {
        if (this.postAction == null) {
            this.postAction = { postAction.invoke(element) }
            return
        }
        throw ComposeBuilderException(
            "Please, use compose functionality correctly! " +
                    "Keep the rule: one `or` <=> one `thenContinue`!"
        )
    }

    fun build(): ComplexComposeBranch<ElementType> =
        ComplexComposeBranch(
            element = element,
            check = check,
            postAction = postAction
        )
}

package com.kaspersky.kaspresso.elementloader

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
 * The interface to provide element loader functionality.
 */
interface ElementLoaderProvider {

    /**
     * Invokes the given [action] and calls [elementLoader] if it fails. Helps in cases when test fails because
     * the element is outdated and must be reloaded using its selectors/matchers.
     *
     * @param elementLoader the lambda to reload the element.
     * @param action the actual action on the interacted view.
     *
     * @return the result of [action] invocation.
     */
    fun <ActionType> passAction(
        elementLoader: () -> Unit,
        action: () -> ActionType
    ): ActionType
}

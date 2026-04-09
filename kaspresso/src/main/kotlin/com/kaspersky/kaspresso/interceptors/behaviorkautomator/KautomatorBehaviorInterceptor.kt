package com.kaspersky.kaspresso.interceptors.behaviorkautomator

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
 * The interface for all interceptors that change the default interaction in Kautomator. Often it wraps the interaction calls.
 */
interface KautomatorBehaviorInterceptor<Interaction, Assertion, Action> {

    /**
     * Called to do some stuff and actually check an interaction with element.
     *
     * @param activity a function-wrapper of an action or an assertion to be invoked.
     */
    fun <T> interceptCheck(interaction: Interaction, assertion: Assertion, activity: () -> T): T

    /**
     * Called to do some stuff and actually perform an interaction with element.
     *
     * @param activity a function-wrapper of an action or an assertion to be invoked.
     */
    fun <T> interceptPerform(interaction: Interaction, action: Action, activity: () -> T): T
}

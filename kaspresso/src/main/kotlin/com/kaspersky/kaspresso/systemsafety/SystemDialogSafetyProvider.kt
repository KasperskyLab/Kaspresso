package com.kaspersky.kaspresso.systemsafety

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
 * An interface to provide system dialog safety functionality.
 */
interface SystemDialogSafetyProvider {

    /**
     * Invokes the given [action] and hides the system dialog if the invocation is failed and the system
     * dialog is actually shown via [suppressSystemDialogs] call.
     *
     * @param action the action to invoke.
     *
     * @return the result of [action]'s invocation.
     */
    fun <T> passSystemDialogs(action: () -> T): T
}

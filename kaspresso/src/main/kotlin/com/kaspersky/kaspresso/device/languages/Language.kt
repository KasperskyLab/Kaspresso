package com.kaspersky.kaspresso.device.languages

import androidx.annotation.MainThread
import java.util.Locale

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
 * The interface to work with languages
 */
interface Language {

    /**
     * Switches language only in the current Application (not in OS!).
     * Please, keep in mind the following fact:
     *   If you have switched languages then activity.recreate() invoked so you have to do it on MainThread only
     *   Also, don't forget to restore the previous language if you don't clean the state of the Application after each test.
     *
     * @throws Throwable if something went wrong
     */
    @MainThread
    fun switchInApp(locale: Locale)

    /**
     * Changes locale for Android OS Settings.
     * Under the hood grants CHANGE_CONFIGURATION permission
     *     (without this permission, it's impossible to change system language)
     *
     * @throws Throwable if something went wrong
     */
    @MainThread
    fun switchInSystem(locale: Locale)
}

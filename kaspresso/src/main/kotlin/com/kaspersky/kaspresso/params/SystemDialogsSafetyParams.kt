package com.kaspersky.kaspresso.params

import java.util.concurrent.TimeUnit

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
 * @param shouldIgnoreKeyboard by default kaspresso treats the keyboard as a system dialog. Enabling this flag prevents a keyboard closing
 * @param shouldIgnoreCrashes whether kaspresso should check if there's a system crash dialog. Be aware that enabling it increases a time spent
 * on a dialog detection about `waitTimeout` * 2
 * @param shouldIgnorePermissionDialogs whether kaspresso should try to automagically close the permission dialogs
 * @param waitTimeout the time spent on a try to detect each of a single type of a dialog
 *
 * @see com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProviderImpl.isAndroidSystemDetected
 * @see com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyPattern
 */
data class SystemDialogsSafetyParams(
    val shouldIgnoreKeyboard: Boolean,
    val shouldIgnoreCrashes: Boolean,
    val waitTimeout: Long = TimeUnit.SECONDS.toMillis(1),
    val shouldIgnorePermissionDialogs: Boolean,
) {
    companion object {
        fun default() = SystemDialogsSafetyParams(
            shouldIgnoreKeyboard = false,
            shouldIgnoreCrashes = true,
            shouldIgnorePermissionDialogs = false
        )
    }
}

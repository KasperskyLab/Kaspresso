package com.kaspersky.kaspresso.device.permissions

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
 * The interface to work with permissions fairly by real permission dialogs.
 */
interface Permissions {

    /**
     * Passes the permission-requesting permissions dialog and allows permissions.
     *
     * @param button - the button which would be pressed. Changing the default value may be useful in android 11+ cases
     * @see (https://developer.android.com/about/versions/11/privacy/location and https://developer.android.com/about/versions/14/changes/partial-photo-video-access)
     */
    fun allowViaDialog(button: Button = Button.ALLOW)

    /**
     * Passes the permission-requesting permissions dialog and denies permissions.
     */
    fun denyViaDialog(button: Button = Button.DENY)

    /**
     * Check the permission-requesting permissions dialog is visible.
     */
    fun isDialogVisible(): Boolean

    /**
     * Passes the permission-requesting permissions dialog
     */
    fun clickOn(button: Button)

    enum class Button {
        ALLOW,
        ALLOW_ALWAYS,
        ALLOW_FOREGROUND,
        ALLOW_ALL,
        ALLOW_SELECTED,
        DENY,
        DENY_AND_DONT_ASK_AGAIN
    }
}

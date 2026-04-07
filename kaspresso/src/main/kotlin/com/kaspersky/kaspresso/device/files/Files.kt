package com.kaspersky.kaspresso.device.files

import com.kaspersky.kaspresso.annotations.RequiresAdbServer

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
 * The interface to work with file permissions.
 *
 * Required: Started AdbServer
 *     1. Download a file "kaspresso/artifacts/adbserver-desktop.jar"
 *     2. Start AdbServer => input in cmd "java jar path_to_file/adbserver-desktop.jar"
 * Methods demanding to use AdbServer in the default implementation of this interface are marked.
 *     But nobody can't deprecate you to write implementation that doesn't require AdbServer.
 */
interface Files {

    /**
     * Performs adb push.
     *
     * Required Permissions: INTERNET.
     *
     * @param serverPath a file path relative to the server directory.
     * @param devicePath a path to copy.
     */
    @RequiresAdbServer
    fun push(serverPath: String, devicePath: String)

    /**
     * Removes a file by given path.
     *
     * Required Permissions: INTERNET
     *
     * @param path a path to remove
     */
    @RequiresAdbServer
    fun remove(path: String)

    /**
     * Performs adb pull.
     *
     * Required Permissions: INTERNET.
     *
     * @param devicePath a file path relative to the device directory.
     * @param serverPath a path to copy. (If empty - pulls in adbServer directory (folder with file "adbserver-desktop.jar"))
     */
    @RequiresAdbServer
    fun pull(devicePath: String, serverPath: String = "")
}

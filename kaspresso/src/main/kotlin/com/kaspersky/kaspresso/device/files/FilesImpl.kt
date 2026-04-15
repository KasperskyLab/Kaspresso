package com.kaspersky.kaspresso.device.files

import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.logger.UiTestLogger

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
 * The implementation of the [Files] interface.
 */
class FilesImpl(
    private val logger: UiTestLogger,
    private val adbServer: AdbServer,
) : Files {

    /**
     * Performs adb push.
     *
     * Required Permissions: INTERNET.
     *
     * @param serverPath a file path relative to the server directory.
     * @param devicePath a path to copy.
     */
    override fun push(serverPath: String, devicePath: String) {
        adbServer.performAdb("push", listOf(serverPath, devicePath))
        logger.i("Push file from $serverPath to $devicePath")
    }

    /**
     * Removes a file by given path.
     *
     * Required Permissions: INTERNET
     *
     * @param path a path to remove
     */
    override fun remove(path: String) {
        adbServer.performShell("rm", listOf("-rf", path))
        logger.i("Remove file from $path")
    }

    /**
     * Performs adb pull.
     *
     * Required Permissions: INTERNET.
     *
     * @param devicePath a file path relative to the device directory.
     * @param serverPath a path to copy. (If empty - pulls in adbServer directory (folder with file "adbserver-desktop.jar"))
     */
    override fun pull(devicePath: String, serverPath: String) {
        adbServer.performCmd("mkdir", listOf("-p", serverPath))
        adbServer.performAdb("pull", listOf(devicePath, serverPath))
        logger.i("Pull file from $devicePath to $serverPath")
    }
}

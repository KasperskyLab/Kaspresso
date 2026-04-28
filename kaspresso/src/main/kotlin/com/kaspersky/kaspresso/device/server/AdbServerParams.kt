package com.kaspersky.kaspresso.device.server

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

import java.util.concurrent.TimeUnit

/**
 * Configuration parameters for the ADB server device-side connection.
 *
 * @param commandTimeoutSeconds how long the device waits for a command result from the desktop
 *        server before declaring a timeout. Defaults to 3 minutes.
 */
data class AdbServerParams(
    val commandTimeoutSeconds: Long = TimeUnit.MINUTES.toSeconds(3)
)

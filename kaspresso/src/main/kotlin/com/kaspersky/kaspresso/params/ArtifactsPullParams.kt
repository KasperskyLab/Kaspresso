package com.kaspersky.kaspresso.params

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

data class ArtifactsPullParams(
    /**
     * Relative path. Absolute one depends on the working directory from which ADB server was started
     */
    val destinationPath: String = ".",

    /**
     * Artifacts would be pulled if it's name fits regex
     */
    val artifactsRegex: Regex = "(screenshots)|(video)|(logcat)|(view_hierarchy)".toRegex(),

    /**
     * Whether Kaspresso should pull the artifacts after a test run. Needs an ADB server to work
     */
    val enabled: Boolean = true
)

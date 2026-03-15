package com.kaspersky.kaspresso.device.screenshots.screenshotfiles

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
 * Provides a directory for screenshots of a separate test
 */
@Deprecated(
    "The work with screenshots and relative resource providers was redesigned.\n" +
            "Please migrate to new system of work with resources presented in 'files/resources' folder.\n" +
            "An example of migration is shown in a secondary constructor of 'DocLocScreenshotTestCase'.")
interface ScreenshotDirectoryProvider {

    /**
     * @param testMethod specifies what test is running
     * @param runNumber specifies how many times a given test have already run
     */
    fun getDirectoryForTest(testMethod: TestMethod, runNumber: Int): String
}

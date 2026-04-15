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

/**
 * @param quality quality of the PNG compression; range: 0-100
 * @param metadataExtractor determines the API used to get metadata from the app
 */
class ScreenshotParams(
    val quality: Int = 100,
    val metadataExtractor: MetadataExtractors = MetadataExtractors.Default,
)

enum class MetadataExtractors {
    /**
     * Traverses XML's views hierarchy to get views metadata
      */
    Default,

    /**
     * Dumps and traverses UI automator tree to get views metadata. Recommended to use for Compose screens
     */
    UiAutomator,
}

package com.kaspersky.components.alluresupport.files

import io.qameta.allure.kotlin.Allure
import java.io.File

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

fun File.attachLogcatToAllureReport(): Unit = Allure.lifecycle.addAttachment(
    name = name,
    stream = this.inputStream(),
    type = "text/plain",
    fileExtension = "txt",
)

fun File.attachViewHierarchyToAllureReport(): Unit = Allure.lifecycle.addAttachment(
    name = name,
    stream = this.inputStream(),
    type = "text/xml",
    fileExtension = "xml",
)

fun File.attachScreenshotToAllureReport(): Unit = Allure.lifecycle.addAttachment(
    name = name,
    stream = this.inputStream(),
    type = "image/png",
    fileExtension = "png",
)

fun File.attachVideoToAllureReport(): Unit = Allure.lifecycle.addAttachment(
    name = name,
    stream = this.inputStream(),
    type = "video/mp4",
    fileExtension = "mp4",
)

package com.kaspersky.kaspresso.docloc.metadata.saver

import com.kaspersky.kaspresso.device.activities.Activities
import com.kaspersky.kaspresso.device.apps.Apps
import com.kaspersky.kaspresso.docloc.metadata.extractor.MetadataExtractor
import com.kaspersky.kaspresso.internal.extensions.other.safeWrite
import com.kaspersky.kaspresso.internal.extensions.other.toXml
import com.kaspersky.kaspresso.logger.UiTestLogger
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

internal class DefaultMetadataSaver(
    private val activities: Activities,
    private val apps: Apps,
    private val logger: UiTestLogger,
    private val metadataExtractor: MetadataExtractor,
) : MetadataSaver {

    override fun saveScreenshotMetadata(folderPath: File, name: String) {
        val activity = activities.getResumed()
        if (activity == null) {
            logger.e("Activity is null when saving metadata $name")
            return
        }
        runCatching {
            val metadata = metadataExtractor.getMetadata().toXml(apps.targetAppPackageName)
            folderPath.resolve("$name.xml").safeWrite(logger, metadata)
        }
    }
}

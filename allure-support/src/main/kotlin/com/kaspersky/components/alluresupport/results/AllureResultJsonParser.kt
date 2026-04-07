package com.kaspersky.components.alluresupport.results

import com.google.common.io.CharStreams
import org.json.JSONObject
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

class AllureResultJsonParser {

    fun getAttachmentSourceFileName(resultFile: File, targetAttachmentName: String): String? {
        var source: String? = null
        resultFile.onEachAttachment { attachment: JSONObject ->
            val attachmentName = attachment.getString(NAME_JSON_FIELD)
            if (attachmentName.equals(targetAttachmentName, ignoreCase = true)) {
                source = attachment.getString(SOURCE_JSON_FIELD)
                return@onEachAttachment true
            }
            return@onEachAttachment false
        }
        return source
    }

    private inline fun File.onEachAttachment(action: (JSONObject) -> Boolean) {
        inputStream().use { inputStream ->
            val json = JSONObject(CharStreams.toString(inputStream.reader()))
            val attachments = json.getJSONArray(ATTACHMENTS_JSON_FIELD)
            for (i in 0 until attachments.length()) {
                val attachment = attachments.getJSONObject(i)
                if (action(attachment)) break
            }
        }
    }

    private companion object {
        const val ATTACHMENTS_JSON_FIELD = "attachments"
        const val NAME_JSON_FIELD = "name"
        const val SOURCE_JSON_FIELD = "source"
    }
}

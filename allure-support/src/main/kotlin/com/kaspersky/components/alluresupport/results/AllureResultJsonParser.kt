package com.kaspersky.components.alluresupport.results

import com.google.common.io.CharStreams
import org.json.JSONObject
import java.io.File

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

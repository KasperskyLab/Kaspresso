package com.kaspersky.kaspresso.docloc

import com.kaspersky.kaspresso.device.activities.Activities
import com.kaspersky.kaspresso.device.activities.metadata.ActivityMetadata
import com.kaspersky.kaspresso.device.apps.Apps
import com.kaspersky.kaspresso.internal.extensions.other.safeWrite
import com.kaspersky.kaspresso.internal.extensions.other.toXml
import com.kaspersky.kaspresso.logger.UiTestLogger
import java.io.File

internal class MetadataSaver(
    private val activities: Activities,
    private val apps: Apps,
    private val logger: UiTestLogger
) {
    private val activityMetadata = ActivityMetadata(logger)

    fun saveScreenshotMetadata(folderPath: File, name: String) {
        val activity = activities.getResumed()
        if (activity == null) {
            logger.e("Activity is null when saving metadata $name")
            return
        }
        runCatching {
            val metadata = activityMetadata.getFromActivity(activity)
                .toXml(apps.targetAppPackageName)
            folderPath.resolve("$name.xml").safeWrite(logger, metadata)
        }
    }
}

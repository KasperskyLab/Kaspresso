package com.kaspersky.kaspresso.docloc.metadata.extractor

import android.app.Activity
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import com.kaspersky.kaspresso.device.activities.Activities
import com.kaspersky.kaspresso.docloc.metadata.LocalizedString
import com.kaspersky.kaspresso.docloc.metadata.Metadata
import com.kaspersky.kaspresso.docloc.metadata.Window

internal class UiMetadataExtractor(
    private val uiDevice: UiDevice,
    private val activities: Activities,
) : MetadataExtractor {

    private val metadataExtractorHelper = MetadataExtractorHelper()

    override fun getMetadata(): Metadata {
        val activity = activities.getResumed() ?: throw RuntimeException("Failed to get current activity")
        return createMetadata(activity)
    }

    private fun createMetadata(activity: Activity): Metadata {
        val localizedStrings = metadataExtractorHelper.resolveAmbiguous(
            getLocalizedStrings()
        )
        val window = activity.window.decorView.run {
            Window(
                left,
                top,
                width,
                height,
                localizedStrings
            )
        }
        return Metadata(window)
    }

    private fun getLocalizedStrings(): List<LocalizedString> {
        return uiDevice.findObjects(By.enabled(true))
            .filterNotNull()
            .filter { !it.text.isNullOrBlank() }
            .map {
                val coords = it.visibleBounds
                LocalizedString(
                    text = it.text,
                    locValueDescription = it.resourceName,
                    left = coords.left,
                    top = coords.top,
                    width = coords.width(),
                    height = coords.height(),
                )
            }
    }
}

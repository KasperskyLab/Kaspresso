package com.kaspersky.kaspresso.docloc.metadata.extractor

import android.app.Activity
import androidx.test.uiautomator.By
import androidx.test.uiautomator.StaleObjectException
import androidx.test.uiautomator.UiDevice
import com.kaspersky.kaspresso.device.activities.Activities
import com.kaspersky.kaspresso.docloc.metadata.LocalizedString
import com.kaspersky.kaspresso.docloc.metadata.Metadata
import com.kaspersky.kaspresso.docloc.metadata.Window
import com.kaspersky.kaspresso.logger.UiTestLogger

internal class UiMetadataExtractor(
    private val uiDevice: UiDevice,
    private val activities: Activities,
    private val logger: UiTestLogger,
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
        uiDevice.setCompressedLayoutHierarchy(false)
        val objectsWithText = uiDevice.findObjects(By.enabled(true))
            .filterNotNull()
            .filter {
                try {
                    (it.resourceName != null && it.children.any { !it.text.isNullOrBlank() }) ||
                            (!it.text.isNullOrBlank() && it.resourceName != null)
                } catch (ex: StaleObjectException) {
                    logger.e("UiMetadataExtractor::getLocalizedStrings() - Error while loading object")
                    false
                }
            }

        val localizedStrings = mutableListOf<LocalizedString>()
        /*
         There might be a case when the text itself won't have an ID, but rather the text container will.
         For example if we set an id to the button, Ui automator will see it as a container with an id and a child - text view without an id

         Probably buggy. Need better solution.
         */
        for (obj in objectsWithText) {
            try {
                val resName = obj.resourceName ?: continue
                if (obj.text.isNullOrBlank()) { // the text container
                    obj.children
                        .filter { !it.text.isNullOrBlank() }
                        .forEach {
                            val coords = it.visibleBounds
                            localizedStrings.add(
                                LocalizedString(
                                    text = it.text,
                                    locValueDescription = resName,
                                    left = coords.left,
                                    top = coords.top,
                                    width = coords.width(),
                                    height = coords.height(),
                                )
                            )
                        }
                } else { // the text itself
                    val coords = obj.visibleBounds
                    localizedStrings.add(
                        LocalizedString(
                            text = obj.text,
                            locValueDescription = resName,
                            left = coords.left,
                            top = coords.top,
                            width = coords.width(),
                            height = coords.height(),
                        )
                    )
                }
            } catch (ex: StaleObjectException) {
                logger.e("UiMetadataExtractor::getLocalizedStrings() - error while processing found objects")
            }
        }

        return localizedStrings
    }
}

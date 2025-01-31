package com.kaspersky.kaspresso.docloc.metadata.extractor

import android.app.Activity
import android.content.res.Resources
import android.view.View
import android.widget.TextView
import androidx.test.espresso.util.TreeIterables
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.kaspersky.kaspresso.device.activities.Activities
import com.kaspersky.kaspresso.docloc.metadata.LocalizedString
import com.kaspersky.kaspresso.docloc.metadata.Metadata
import com.kaspersky.kaspresso.docloc.metadata.Window
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 * The utility class to collect metadata from a window.
 */
internal class ActivityMetadataExtractor(
    private val logger: UiTestLogger,
    private val activities: Activities,
) : MetadataExtractor {

    private val metadataExtractorHelper = MetadataExtractorHelper()

    override fun getMetadata(): Metadata {
        val activity = activities.getResumed() ?: throw RuntimeException("Failed to get current activity")
        return getFromActivity(activity)
    }

    /**
     * Returns a formed metadata object for a given activity, by collecting all visible [TextView] data.
     * If several elements have the same id, surrogate index will be added.
     * If android cannot get view's id, it will be written as [id:<id>].
     *
     * @param activity activity to collect metadata from.
     * @return Metadata for the activity.
     */
    private fun getFromActivity(activity: Activity): Metadata {
        return createMetadata(activity)
    }

    private fun createMetadata(activity: Activity): Metadata {
        with(activity.window.decorView) {
            val localizedStrings = metadataExtractorHelper.resolveAmbiguous(
                getLocalizedStrings(this)
            )
            val window = Window(
                left,
                top,
                width,
                height,
                localizedStrings
            )
            return Metadata(window)
        }
    }

    private fun getLocalizedStrings(decorView: View): List<LocalizedString> {
        return TreeIterables.depthFirstViewTraversal(decorView)
            .filter { it.visibility == View.VISIBLE }
            .mapNotNull { v ->
                when (v) {
                    is TextView -> LocalizedString(
                        v.text.toString(),
                        getEntryName(decorView.resources, v),
                        v.left,
                        v.top,
                        v.width,
                        v.height
                    )
                    is CollapsingToolbarLayout -> LocalizedString(
                        v.title.toString(),
                        getEntryNameFromLayout(decorView.resources, v),
                        v.left,
                        v.top,
                        v.width,
                        v.height
                    )
                    else -> null
                }
            }
    }

    private fun getEntryName(resources: Resources, v: TextView): String {
        return try {
            resources.getResourceEntryName(v.id)
        } catch (ex: Resources.NotFoundException) {
            logger.e("Entry ${v.id} not found for TextView with text ${v.text}")
            "[id:${Integer.toHexString(v.id)}]"
        }
    }

    private fun getEntryNameFromLayout(resources: Resources, layout: CollapsingToolbarLayout): String {
        return try {
            resources.getResourceEntryName(layout.id)
        } catch (ex: Resources.NotFoundException) {
            logger.e("Entry ${layout.id} not found")
            "[id:${Integer.toHexString(layout.id)}]"
        }
    }
}

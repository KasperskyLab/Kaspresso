package com.kaspersky.kaspresso.device.activities.metadata

import android.app.Activity
import android.content.res.Resources
import android.view.View
import android.widget.TextView
import androidx.test.espresso.util.TreeIterables
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 * The utility class to collect metadata from a window.
 */
internal class ActivityMetadata(
    private val logger: UiTestLogger
) {

    companion object {
        private const val INDEX_SEPARATOR = '_'
    }

    /**
     * Returns a formed metadata object for a given activity, by collecting all visible [TextView] data.
     * If several elements have the same id, surrogate index will be added.
     * If android cannot get view's id, it will be written as [id:<id>].
     *
     * @param activity activity to collect metadata from.
     * @return Metadata for the activity.
     */
    internal fun getFromActivity(activity: Activity): Metadata {
        return getMetadata(activity)
    }

    private fun getMetadata(activity: Activity): Metadata {
        with(activity.window.decorView) {
            val localizedStrings =
                resolveAmbiguous(
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

    private fun resolveAmbiguous(localizedStrings: List<LocalizedString>): List<LocalizedString> {
        return localizedStrings.groupBy { it.locValueDescription }
            .values
            .flatMap { groupedById ->
                if (groupedById.size == 1) groupedById else addIndexes(
                    groupedById
                )
            }
    }

    private fun addIndexes(groupedById: List<LocalizedString>): List<LocalizedString> {
        return groupedById.mapIndexed { index, locString ->
            locString.copy(locValueDescription = "${locString.locValueDescription}$INDEX_SEPARATOR${index + 1}")
        }
    }
}

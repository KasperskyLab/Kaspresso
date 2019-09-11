package com.kaspersky.kaspresso.internal.extensions.other

import android.text.TextUtils.htmlEncode
import com.kaspersky.kaspresso.device.activities.metadata.LocalizedString
import com.kaspersky.kaspresso.device.activities.metadata.Metadata

/**
 * Transforms [Metadata] object to an xml string.
 *
 * @param productPackage package of application.
 * @return serialized Metadata string.
 */
internal fun Metadata.toXml(productPackage: String): String {
    val locStringsXml = window.localizedStrings.joinToString(separator = "") { it.toXml(productPackage) }
    val windowXml = """<Window Left="${window.left}" Top="${window.top}" Width="${window.width}" Height="${window.height}">$locStringsXml</Window>"""
    return """<Metadata>$windowXml</Metadata>"""
}

/**
 * Transforms [LocalizedString] object to an xml string.
 *
 * @return serialized LocalizedString string.
 */
internal fun LocalizedString.toXml(locPrefix: String): String {
    return """<LocalizedString Text="${htmlEncode(text)}" LocValueDescription="$locPrefix:id/$locValueDescription" Top="$top" Left="$left" Width="$width" Height="$height"/>"""
}

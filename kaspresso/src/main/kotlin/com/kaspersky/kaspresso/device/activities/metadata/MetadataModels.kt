package com.kaspersky.kaspresso.device.activities.metadata

internal data class Metadata(val window: Window)

internal data class Window(
    val left: Int,
    val top: Int,
    val width: Int,
    val height: Int,
    val localizedStrings: List<LocalizedString>
)

internal data class LocalizedString(
    val text: String,
    val locValueDescription: String,
    val left: Int,
    val top: Int,
    val width: Int,
    val height: Int
)

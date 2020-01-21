package com.kaspersky.kaspresso.objectloader

import com.kaspersky.components.kautomator.intercepting.interaction.UiObjectInteraction

interface ObjectLoaderProvider {

    fun <T> handleObjectAbsence(interaction: UiObjectInteraction, action: () -> T): T
}
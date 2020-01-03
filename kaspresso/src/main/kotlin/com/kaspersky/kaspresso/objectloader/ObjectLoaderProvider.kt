package com.kaspersky.kaspresso.objectloader

import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiObjectInteraction

interface ObjectLoaderProvider {

    fun <T> handleObjectAbsence(interaction: UiObjectInteraction, action: () -> T): T
}
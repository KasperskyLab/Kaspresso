package com.kaspersky.kaspresso.interceptors.behaviorKautomator.impl

import com.kaspersky.components.kautomator.intercepting.interaction.UiObjectInteraction
import com.kaspersky.components.kautomator.intercepting.operation.UiObjectAction
import com.kaspersky.components.kautomator.intercepting.operation.UiObjectAssertion
import com.kaspersky.kaspresso.interceptors.behaviorKautomator.ObjectBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.objectloader.ObjectLoaderProvider
import com.kaspersky.kaspresso.objectloader.ObjectLoaderProviderImpl

class ObjectLoaderBehaviorInterceptor(
    private val logger: UiTestLogger
) : ObjectBehaviorInterceptor,
    ObjectLoaderProvider by ObjectLoaderProviderImpl(logger) {

    override fun <T> interceptCheck(
        interaction: UiObjectInteraction,
        assertion: UiObjectAssertion,
        activity: () -> T
    ): T = handleObjectAbsence(interaction = interaction, action = activity)

    override fun <T> interceptPerform(
        interaction: UiObjectInteraction,
        action: UiObjectAction,
        activity: () -> T
    ): T = handleObjectAbsence(interaction = interaction, action = activity)
}
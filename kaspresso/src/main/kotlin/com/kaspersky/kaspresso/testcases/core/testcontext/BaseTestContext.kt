package com.kaspersky.kaspresso.testcases.core.testcontext

import com.kaspersky.kaspresso.compose.ComposeProvider
import com.kaspersky.kaspresso.compose.WebComposeProvider
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.device.Device
import com.kaspersky.kaspresso.flakysafety.FlakySafetyParams
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider
import com.kaspersky.kaspresso.logger.UiTestLogger

open class BaseTestContext internal constructor(
    final override val configurator: Configurator
) : FlakySafetyProvider, ComposeProvider, WebComposeProvider {

    override val params: FlakySafetyParams = configurator.flakySafetyParams
    override val logger: UiTestLogger = configurator.libLogger

    val device: Device = configurator.device

    val testLogger: UiTestLogger = configurator.testLogger
}
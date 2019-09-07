package com.kaspersky.kaspresso.testcases.core.testcontext

import com.kaspersky.kaspresso.compose.ComposeProvider
import com.kaspersky.kaspresso.compose.WebComposeProvider
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.device.Device
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderImpl
import com.kaspersky.kaspresso.logger.UiTestLogger

open class BaseTestContext internal constructor(
    final override val configurator: Configurator
) : FlakySafetyProvider by FlakySafetyProviderImpl(configurator.flakySafetyParams, configurator.libLogger),
    ComposeProvider,
    WebComposeProvider {

    val device: Device = configurator.device
    val adbServer: AdbServer = configurator.adbServer
    val testLogger: UiTestLogger = configurator.testLogger
}
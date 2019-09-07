package com.kaspersky.kaspresso.testcases.core.testcontext

import com.kaspersky.kaspresso.compose.ComposeProvider
import com.kaspersky.kaspresso.compose.ComposeProviderImpl
import com.kaspersky.kaspresso.compose.WebComposeProvider
import com.kaspersky.kaspresso.compose.WebComposeProviderImpl
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.device.Device
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderImpl
import com.kaspersky.kaspresso.logger.UiTestLogger

open class BaseTestContext internal constructor(
    configurator: Configurator
) : FlakySafetyProvider by FlakySafetyProviderImpl(configurator.flakySafetyParams, configurator.libLogger),
    ComposeProvider by ComposeProviderImpl(configurator),
    WebComposeProvider by WebComposeProviderImpl(configurator) {

    val device: Device = configurator.device
    val adbServer: AdbServer = configurator.adbServer
    val testLogger: UiTestLogger = configurator.testLogger
}
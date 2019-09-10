package com.kaspersky.kaspresso.testcases.core.testcontext

import com.kaspersky.kaspresso.compose.ComposeProvider
import com.kaspersky.kaspresso.compose.ComposeProviderImpl
import com.kaspersky.kaspresso.compose.WebComposeProvider
import com.kaspersky.kaspresso.compose.WebComposeProviderImpl
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.device.Device
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderImpl
import com.kaspersky.kaspresso.logger.UiTestLogger

open class BaseTestContext internal constructor(
    kaspresso: Kaspresso
) : FlakySafetyProvider by FlakySafetyProviderImpl(kaspresso.flakySafetyParams, kaspresso.libLogger),
    ComposeProvider by ComposeProviderImpl(kaspresso),
    WebComposeProvider by WebComposeProviderImpl(kaspresso) {

    val device: Device = kaspresso.device
    val adbServer: AdbServer = kaspresso.adbServer
    val testLogger: UiTestLogger = kaspresso.testLogger
}
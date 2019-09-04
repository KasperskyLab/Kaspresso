package com.kaspersky.kaspresso.testcases.core.testcontext

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.device.Device
import com.kaspersky.kaspresso.logger.KLogger
import com.kaspersky.kaspresso.testcases.core.testcontext.provide.attempt.AttemptProvider
import com.kaspersky.kaspresso.testcases.core.testcontext.provide.compose.*
import com.kaspersky.kaspresso.testcases.core.testcontext.provide.wait.WaitProvider

open class BaseTestContext internal constructor(
    configurator: Configurator
) : AttemptProvider, WaitProvider, ComposeProvider, WebComposeProvider {

    override val params: FlakySafetyParams = configurator.flakySafetyParams
    override val logger: UiTestLogger = configurator.libLogger

    val device: Device = configurator.device

    val testLogger: UiTestLogger = configurator.testLogger
}
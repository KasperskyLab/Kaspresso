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

    private val composer: Composer = Composer(configurator)
    private val webComposer: WebComposer = WebComposer(configurator)

    val device: Device = Device(configurator)

    val testLogger: UiTestLogger = configurator.testLogger
}
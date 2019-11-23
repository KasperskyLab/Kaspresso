package com.kaspersky.kaspresso.testcases.core.testassistants

import com.kaspersky.kaspresso.device.Device
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.logger.UiTestLogger

internal class TestAssistantsProviderImpl(kaspresso: Kaspresso) : TestAssistantsProvider {

    override val device: Device = kaspresso.device
    override val adbServer: AdbServer = kaspresso.adbServer
    override val testLogger: UiTestLogger = kaspresso.testLogger
}
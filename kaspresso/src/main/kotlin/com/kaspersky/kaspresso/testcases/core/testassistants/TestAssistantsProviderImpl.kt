package com.kaspersky.kaspresso.testcases.core.testassistants

import com.kaspersky.kaspresso.device.Device
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.files.resources.ResourceFilesProvider
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.Params

internal class TestAssistantsProviderImpl(kaspresso: Kaspresso) : TestAssistantsProvider {

    override val device: Device = kaspresso.device
    override val adbServer: AdbServer = kaspresso.adbServer
    override val testLogger: UiTestLogger = kaspresso.testLogger
    override val params: Params = kaspresso.params
    override val resourceFilesProvider: ResourceFilesProvider = kaspresso.resourceFilesProvider
}

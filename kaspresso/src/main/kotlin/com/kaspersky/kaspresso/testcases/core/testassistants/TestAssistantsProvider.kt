package com.kaspersky.kaspresso.testcases.core.testassistants

import com.kaspersky.kaspresso.device.Device
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.files.resources.ResourceFilesProvider
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.Params

/**
 * Provider of test assistants allowed in [com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext],
 * [com.kaspersky.kaspresso.testcases.api.testcase.BaseTestCase] and their inheritors
 */
interface TestAssistantsProvider {

    val device: Device
    val adbServer: AdbServer
    val testLogger: UiTestLogger
    val params: Params
    val resourceFilesProvider: ResourceFilesProvider
}

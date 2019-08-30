package com.kaspersky.kaspresso.testcases.core.testcontext

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.device.Device
import com.kaspersky.kaspresso.logger.KLogger

open class BaseTestContext internal constructor(configurator: Configurator) : Composer(configurator) {

    val device: Device = Device(configurator)

    val kLogger: KLogger = KLogger(configurator.externalLogger)
}
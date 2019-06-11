package com.kaspersky.kaspresso.testcases.core

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.device.Device
import com.kaspersky.kaspresso.logger.KLogger

open class BaseTestContext(configurator: Configurator) {

    val device: Device = Device(configurator)

    val kLogger: KLogger = KLogger(configurator.externalLogger)
}
package com.kaspersky.kaspresso.device.network

import java.lang.RuntimeException

class DeviceNetworkException(override val message: String) : RuntimeException(message)
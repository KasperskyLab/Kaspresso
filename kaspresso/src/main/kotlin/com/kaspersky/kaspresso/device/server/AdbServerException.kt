package com.kaspersky.kaspresso.device.server

import java.lang.RuntimeException

class AdbServerException(override val message: String) : RuntimeException(message)
package com.kaspersky.testserver

import com.kaspersky.testserver.api.Command

data class AdbCommand(override val body: String) : Command(body)

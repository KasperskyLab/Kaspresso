package com.kaspersky.adbserver

import com.kaspersky.adbserver.api.Command

data class AdbCommand(override val body: String) : Command(body)

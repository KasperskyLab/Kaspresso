package com.kaspersky.test_server

import com.kaspersky.test_server.api.Command

data class AdbCommand(override val body: String) : Command(body)
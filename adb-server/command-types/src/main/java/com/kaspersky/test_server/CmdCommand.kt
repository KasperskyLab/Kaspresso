package com.kaspersky.test_server

import com.kaspersky.test_server.api.Command

data class CmdCommand(override val body: String) : Command(body)
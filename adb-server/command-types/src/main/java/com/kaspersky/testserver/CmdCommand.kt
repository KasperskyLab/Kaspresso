package com.kaspersky.testserver

import com.kaspersky.testserver.api.Command

data class CmdCommand(override val body: String) : Command(body)

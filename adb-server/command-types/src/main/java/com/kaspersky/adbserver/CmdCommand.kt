package com.kaspersky.adbserver

import com.kaspersky.adbserver.api.Command

data class CmdCommand(override val body: String) : Command(body)

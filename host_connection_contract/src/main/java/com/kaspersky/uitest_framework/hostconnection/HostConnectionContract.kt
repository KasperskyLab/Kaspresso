package com.kaspersky.uitest_framework.hostconnection

import com.kaspersky.command_handler.Command

const val PORT: Int = 8500

class CmdException(message: String) : RuntimeException(message)

data class CmdCommand(val body: String) : Command<String>()

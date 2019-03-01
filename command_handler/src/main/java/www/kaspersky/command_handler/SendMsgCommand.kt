package www.kaspersky.command_handler

data class SendMsgCommand(val id: Long, val body: String) : Command<Any>()
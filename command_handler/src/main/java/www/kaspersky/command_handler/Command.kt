package www.kaspersky.command_handler

import java.io.Serializable

abstract class Command<T> protected constructor() : Serializable

interface ICommandExecutor {
    @Throws(Exception::class)
    fun <T> execute(command: Command<T>): T
}

interface ICommandHandler {

    fun <T> accept(command: Command<T>): Boolean

    @Throws(Exception::class)
    fun <T> execute(command: Command<*>): T
}
package www.kaspersky.command_handler.local

import www.kaspersky.command_handler.Command
import www.kaspersky.command_handler.ICommandExecutor
import www.kaspersky.command_handler.ICommandHandler
import java.util.ArrayList

class LocalCommandExecutor : ICommandExecutor {

    private val mHandlers = ArrayList<ICommandHandler>()

    @Throws(Exception::class)
    override fun <T> execute(command: Command<T>): T {
        for (handler in mHandlers) {
            if (handler.accept(command)) {
                return handler.execute(command)
            }
        }
        throw RuntimeException("Unknown command : $command")
    }

    fun addHandler(handler: ICommandHandler): LocalCommandExecutor {
        mHandlers.add(handler)
        return this
    }
}

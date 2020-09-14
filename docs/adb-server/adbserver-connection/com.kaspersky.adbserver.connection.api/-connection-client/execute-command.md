//[adbserver-connection](../../index.md)/[com.kaspersky.adbserver.connection.api](../index.md)/[ConnectionClient](index.md)/[executeCommand](execute-command.md)



# executeCommand  
[jvm]  
Brief description  


It is synchronous method to not reorder a line of commands because if commands were completed in incorrect order it may to lead inconsistent state of the app and the device

  
Content  
abstract fun [executeCommand](execute-command.md)(command: Command): CommandResult  




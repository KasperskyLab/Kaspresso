[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.server](../index.md) / [AdbServer](index.md) / [performShell](./perform-shell.md)

# performShell

`abstract fun performShell(vararg commands: String): List<String>`

Performs shell commands blocking current thread.
Please be aware! If any command that is in @param commands failed then AdbServerException will be thrown

Required Permissions: INTERNET.

### Parameters

`commands` - commands to execute.

### Exceptions

`AdbServerException` - if a result status of any command in @param commands is Failed

**Return**
list of answers of commands' execution


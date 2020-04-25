[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.server](../index.md) / [AdbServerImpl](index.md) / [performAdb](./perform-adb.md)

# performAdb

`fun performAdb(vararg commands: String): List<String>`

Performs adb commands blocking current thread.
Please be aware! If any command that is in @param commands failed then AdbServerException will be thrown

Required Permissions: INTERNET.

### Parameters

`commands` - commands to execute.

### Exceptions

`AdbServerException` - if a result status of any command in @param commands is Failed

**Return**
list of answers of commands' execution


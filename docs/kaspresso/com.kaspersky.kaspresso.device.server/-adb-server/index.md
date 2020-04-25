[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.server](../index.md) / [AdbServer](./index.md)

# AdbServer

`interface AdbServer`

This is a comfortable wrapper to work with AdbServer repository.
Important notes:

1. Real connection is established only after a call one of methods of the interface except disconnectServer().
So it's lazy wrapper. Keep it in your mind when you decide to put custom implementation od AdbServer.
2. After each test a developer has to disconnect AdbServer. There is disconnectServer() method to complete the disconnection.
But Kaspresso calls disconnectServer() after each test if the connection was established during the test. What's why don't worry =)

### Functions

| Name | Summary |
|---|---|
| [disconnectServer](disconnect-server.md) | Disconnect from AdbServer. The method is called by Kaspresso after each test.`abstract fun disconnectServer(): Unit` |
| [performAdb](perform-adb.md) | Performs adb commands blocking current thread. Please be aware! If any command that is in @param commands failed then AdbServerException will be thrown`abstract fun performAdb(vararg commands: String): List<String>` |
| [performCmd](perform-cmd.md) | Executes shell commands blocking current thread. Please be aware! If any command that is in @param commands failed then AdbServerException will be thrown`abstract fun performCmd(vararg commands: String): List<String>` |
| [performShell](perform-shell.md) | Performs shell commands blocking current thread. Please be aware! If any command that is in @param commands failed then AdbServerException will be thrown`abstract fun performShell(vararg commands: String): List<String>` |

### Inheritors

| Name | Summary |
|---|---|
| [AdbServerImpl](../-adb-server-impl/index.md) | The implementation of [AdbServer](./index.md) interface. Encapsulates all work with adb server.`class AdbServerImpl : `[`AdbServer`](./index.md) |

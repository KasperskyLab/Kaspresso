[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.server](../index.md) / [AdbServer](./index.md)

# AdbServer

`object AdbServer`

Encapsulates all work with adb server.

### Functions

| Name | Summary |
|---|---|
| [performAdb](perform-adb.md) | `fun performAdb(vararg commands: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Performs adb commands blocking current thread. |
| [performCmd](perform-cmd.md) | `fun performCmd(vararg commands: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Executes shell commands blocking current thread. |
| [performShell](perform-shell.md) | `fun performShell(vararg commands: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Performs shell commands blocking current thread. |

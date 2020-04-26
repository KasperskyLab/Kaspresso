[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.server](../index.md) / [AdbServerImpl](./index.md)

# AdbServerImpl

`class AdbServerImpl : `[`AdbServer`](../-adb-server/index.md)

The implementation of [AdbServer](../-adb-server/index.md) interface. Encapsulates all work with adb server.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The implementation of [AdbServer](../-adb-server/index.md) interface. Encapsulates all work with adb server.`AdbServerImpl(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [disconnectServer](disconnect-server.md) | Disconnect from AdbServer. The method is called by Kaspresso after each test.`fun disconnectServer(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [performAdb](perform-adb.md) | Performs adb commands blocking current thread. Please be aware! If any command that is in @param commands failed then AdbServerException will be thrown`fun performAdb(vararg commands: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [performCmd](perform-cmd.md) | Executes shell commands blocking current thread. Please be aware! If any command that is in @param commands failed then AdbServerException will be thrown`fun performCmd(vararg commands: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [performShell](perform-shell.md) | Performs shell commands blocking current thread. Please be aware! If any command that is in @param commands failed then AdbServerException will be thrown`fun performShell(vararg commands: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |

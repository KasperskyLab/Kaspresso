[kaspresso](../index.md) / [com.kaspersky.kaspresso.flakysafety](index.md) / [wait](./wait.md)

# wait

`fun <T> wait(timeoutMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = Configurator.attemptsTimeoutMs, logger: `[`UiTestLogger`](../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md)` = Configurator.logger, action: () -> `[`T`](wait.md#T)`): `[`T`](wait.md#T)

Waits for [timeoutMs](wait.md#com.kaspersky.kaspresso.flakysafety$wait(kotlin.Long, com.kaspersky.kaspresso.logger.UiTestLogger, kotlin.Function0((com.kaspersky.kaspresso.flakysafety.wait.T)))/timeoutMs) and invokes an [action](wait.md#com.kaspersky.kaspresso.flakysafety$wait(kotlin.Long, com.kaspersky.kaspresso.logger.UiTestLogger, kotlin.Function0((com.kaspersky.kaspresso.flakysafety.wait.T)))/action).

### Parameters

`timeoutMs` - a time to wait in milliseconds.

`logger` - a logger to log errors.

`action` - an action that to be invoked.

**Return**
[T](wait.md#T) as it is a result of [action](wait.md#com.kaspersky.kaspresso.flakysafety$wait(kotlin.Long, com.kaspersky.kaspresso.logger.UiTestLogger, kotlin.Function0((com.kaspersky.kaspresso.flakysafety.wait.T)))/action) invocation.


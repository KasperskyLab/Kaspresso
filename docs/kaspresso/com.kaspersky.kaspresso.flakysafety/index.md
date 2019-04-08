[kaspresso](../index.md) / [com.kaspersky.kaspresso.flakysafety](./index.md)

## Package com.kaspersky.kaspresso.flakysafety

### Functions

| Name | Summary |
|---|---|
| [attempt](attempt.md) | `fun <T> attempt(timeoutMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = Configurator.attemptsTimeoutMs, intervalMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = Configurator.attemptsIntervalMs, logger: `[`UiTestLogger`](../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md)` = Configurator.logger, allowedExceptions: `[`Set`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)`<`[`Class`](https://developer.android.com/reference/java/lang/Class.html)`<out `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`>> = Configurator.allowedExceptionsForAttempt, action: () -> `[`T`](attempt.md#T)`): `[`T`](attempt.md#T)<br>Makes several attempts to invoke an action. |
| [wait](wait.md) | `fun <T> wait(timeoutMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = Configurator.attemptsTimeoutMs, logger: `[`UiTestLogger`](../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md)` = Configurator.logger, action: () -> `[`T`](wait.md#T)`): `[`T`](wait.md#T)<br>Waits for [timeoutMs](wait.md#com.kaspersky.kaspresso.flakysafety$wait(kotlin.Long, com.kaspersky.kaspresso.logger.UiTestLogger, kotlin.Function0((com.kaspersky.kaspresso.flakysafety.wait.T)))/timeoutMs) and invokes an [action](wait.md#com.kaspersky.kaspresso.flakysafety$wait(kotlin.Long, com.kaspersky.kaspresso.logger.UiTestLogger, kotlin.Function0((com.kaspersky.kaspresso.flakysafety.wait.T)))/action). |

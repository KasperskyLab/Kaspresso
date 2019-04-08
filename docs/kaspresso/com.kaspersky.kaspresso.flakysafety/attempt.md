[kaspresso](../index.md) / [com.kaspersky.kaspresso.flakysafety](index.md) / [attempt](./attempt.md)

# attempt

`fun <T> attempt(timeoutMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = Configurator.attemptsTimeoutMs, intervalMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = Configurator.attemptsIntervalMs, logger: `[`UiTestLogger`](../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md)` = Configurator.logger, allowedExceptions: `[`Set`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)`<`[`Class`](https://developer.android.com/reference/java/lang/Class.html)`<out `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`>> = Configurator.allowedExceptionsForAttempt, action: () -> `[`T`](attempt.md#T)`): `[`T`](attempt.md#T)

Makes several attempts to invoke an action.

### Parameters

`timeoutMs` - a timeout for all attempts in milliseconds.

`intervalMs` - an interval between attempts in milliseconds.

`logger` - a logger to log errors.

`allowedExceptions` - exceptions that doesn't stop attempts.

`action` - an action that is attempted to be invoked.

**Return**
[T](attempt.md#T) as it is a result of [action](attempt.md#com.kaspersky.kaspresso.flakysafety$attempt(kotlin.Long, kotlin.Long, com.kaspersky.kaspresso.logger.UiTestLogger, kotlin.collections.Set((java.lang.Class((kotlin.Throwable)))), kotlin.Function0((com.kaspersky.kaspresso.flakysafety.attempt.T)))/action) invocation.


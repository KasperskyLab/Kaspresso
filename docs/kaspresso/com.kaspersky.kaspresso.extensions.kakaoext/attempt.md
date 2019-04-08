[kaspresso](../index.md) / [com.kaspersky.kaspresso.extensions.kakaoext](index.md) / [attempt](./attempt.md)

# attempt

`fun <T : KBaseView<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>, R> `[`T`](attempt.md#T)`.attempt(timeoutMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = Configurator.attemptsTimeoutMs, intervalMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = Configurator.attemptsIntervalMs, logger: `[`UiTestLogger`](../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md)` = Configurator.logger, allowedExceptions: `[`Set`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)`<`[`Class`](https://developer.android.com/reference/java/lang/Class.html)`<out `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`>> = Configurator.allowedExceptionsForAttempt, action: `[`T`](attempt.md#T)`.() -> `[`R`](attempt.md#R)`): `[`R`](attempt.md#R)

Provides an [com.kaspersky.kaspresso.flakysafety.attempt](../com.kaspersky.kaspresso.flakysafety/attempt.md) method as an extension of [KBaseView](#).

`fun <T : KBaseView<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>, R> `[`T`](attempt.md#T)`.attempt(timeoutSec: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`, action: `[`T`](attempt.md#T)`.() -> `[`R`](attempt.md#R)`): `[`R`](attempt.md#R)

Provides a simplified [com.kaspersky.kaspresso.flakysafety.attempt](../com.kaspersky.kaspresso.flakysafety/attempt.md) method as an extension of [KBaseView](#).


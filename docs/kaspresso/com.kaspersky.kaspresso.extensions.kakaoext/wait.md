[kaspresso](../index.md) / [com.kaspersky.kaspresso.extensions.kakaoext](index.md) / [wait](./wait.md)

# wait

`fun <T : KBaseView<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>, R> `[`T`](wait.md#T)`.wait(timeoutMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = Configurator.attemptsTimeoutMs, logger: `[`UiTestLogger`](../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md)` = Configurator.logger, action: `[`T`](wait.md#T)`.() -> `[`R`](wait.md#R)`): `[`R`](wait.md#R)

Provides a [com.kaspersky.kaspresso.flakysafety.wait](../com.kaspersky.kaspresso.flakysafety/wait.md) method as an extension of [KBaseView](#).

`fun <T : KBaseView<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>, R> `[`T`](wait.md#T)`.wait(timeoutSec: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`, action: `[`T`](wait.md#T)`.() -> `[`R`](wait.md#R)`): `[`R`](wait.md#R)

Provides a simplified [com.kaspersky.kaspresso.flakysafety.wait](../com.kaspersky.kaspresso.flakysafety/wait.md) method as an extension of [KBaseView](#).


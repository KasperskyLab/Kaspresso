[kaspresso](../../index.md) / [com.kaspersky.kaspresso.flakysafety](../index.md) / [CheckDuringProviderImpl](./index.md)

# CheckDuringProviderImpl

`class CheckDuringProviderImpl : `[`CheckDuringProvider`](../-check-during-provider/index.md)

The implementation of the [CheckDuringProvider](../-check-during-provider/index.md) interface.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `CheckDuringProviderImpl(params: `[`CheckDuringParams`](../../com.kaspersky.kaspresso.params/-check-during-params/index.md)`, logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`)`<br>The implementation of the [CheckDuringProvider](../-check-during-provider/index.md) interface. |

### Functions

| Name | Summary |
|---|---|
| [checkDuring](check-during.md) | `fun <T> checkDuring(action: () -> `[`T`](check-during.md#T)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun <T> checkDuring(timeoutMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`?, intervalMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`?, failureMessage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?, action: () -> `[`T`](check-during.md#T)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Invokes the given [action](check-during.md#com.kaspersky.kaspresso.flakysafety.CheckDuringProviderImpl$checkDuring(kotlin.Function0((com.kaspersky.kaspresso.flakysafety.CheckDuringProviderImpl.checkDuring.T)))/action) during set timeout. |

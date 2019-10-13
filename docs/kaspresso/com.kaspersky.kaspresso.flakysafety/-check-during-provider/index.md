[kaspresso](../../index.md) / [com.kaspersky.kaspresso.flakysafety](../index.md) / [CheckDuringProvider](./index.md)

# CheckDuringProvider

`interface CheckDuringProvider`

The interface to provide the flaky safety functionality.

### Functions

| Name | Summary |
|---|---|
| [checkDuring](check-during.md) | `abstract fun <T> checkDuring(action: () -> `[`T`](check-during.md#T)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`abstract fun <T> checkDuring(timeoutMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`? = null, intervalMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`? = null, failureMessage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, action: () -> `[`T`](check-during.md#T)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Invokes the given [action](check-during.md#com.kaspersky.kaspresso.flakysafety.CheckDuringProvider$checkDuring(kotlin.Function0((com.kaspersky.kaspresso.flakysafety.CheckDuringProvider.checkDuring.T)))/action) during set timeout. |

### Inheritors

| Name | Summary |
|---|---|
| [BaseTestContext](../../com.kaspersky.kaspresso.testcases.core.testcontext/-base-test-context/index.md) | `open class BaseTestContext : `[`FlakySafetyProvider`](../-flaky-safety-provider/index.md)`, `[`CheckDuringProvider`](./index.md)`, `[`ComposeProvider`](../../com.kaspersky.kaspresso.compose/-compose-provider/index.md)`, `[`WebComposeProvider`](../../com.kaspersky.kaspresso.compose/-web-compose-provider/index.md)<br>Provides the Kaspresso functionality for "run" section: [Device](../../com.kaspersky.kaspresso.device/-device/index.md), [AdbServer](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md), the [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md) implementation for external developers. Also provides flaky safety, composing and web composing functionalities via implementing [FlakySafetyProvider](../-flaky-safety-provider/index.md), [ComposeProvider](../../com.kaspersky.kaspresso.compose/-compose-provider/index.md) and [WebComposeProvider](../../com.kaspersky.kaspresso.compose/-web-compose-provider/index.md) interfaces. |
| [CheckDuringProviderImpl](../-check-during-provider-impl/index.md) | `class CheckDuringProviderImpl : `[`CheckDuringProvider`](./index.md)<br>The implementation of the [CheckDuringProvider](./index.md) interface. |

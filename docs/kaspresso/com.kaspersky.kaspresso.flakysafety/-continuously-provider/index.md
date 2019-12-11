[kaspresso](../../index.md) / [com.kaspersky.kaspresso.flakysafety](../index.md) / [ContinuouslyProvider](./index.md)

# ContinuouslyProvider

`interface ContinuouslyProvider`

The interface to provide the flaky safety functionality.

### Functions

| Name | Summary |
|---|---|
| [continuously](continuously.md) | `abstract fun <T> continuously(action: () -> `[`T`](continuously.md#T)`): `[`T`](continuously.md#T)<br>`abstract fun <T> continuously(timeoutMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`? = null, intervalMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`? = null, failureMessage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, action: () -> `[`T`](continuously.md#T)`): `[`T`](continuously.md#T)<br>Invokes the given [action](continuously.md#com.kaspersky.kaspresso.flakysafety.ContinuouslyProvider$continuously(kotlin.Function0((com.kaspersky.kaspresso.flakysafety.ContinuouslyProvider.continuously.T)))/action) during set timeout. |

### Inheritors

| Name | Summary |
|---|---|
| [BaseTestContext](../../com.kaspersky.kaspresso.testcases.core.testcontext/-base-test-context.md) | `open class BaseTestContext : `[`FlakySafetyProvider`](../-flaky-safety-provider/index.md)`, `[`ContinuouslyProvider`](./index.md)`, `[`ComposeProvider`](../../com.kaspersky.kaspresso.compose/-compose-provider/index.md)`, `[`WebComposeProvider`](../../com.kaspersky.kaspresso.compose/-web-compose-provider/index.md)`, `[`TestAssistantsProvider`](../../com.kaspersky.kaspresso.testcases.core.testassistants/-test-assistants-provider/index.md)<br>Provides the Kaspresso functionality for "run" section: [Device](../../com.kaspersky.kaspresso.device/-device/index.md), [AdbServer](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md), the [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md) implementation for external developers. Also provides flaky safety, composing and web composing functionalities via implementing [FlakySafetyProvider](../-flaky-safety-provider/index.md), [ComposeProvider](../../com.kaspersky.kaspresso.compose/-compose-provider/index.md) and [WebComposeProvider](../../com.kaspersky.kaspresso.compose/-web-compose-provider/index.md) interfaces. |
| [ContinuouslyProviderImpl](../-continuously-provider-impl/index.md) | `class ContinuouslyProviderImpl : `[`ContinuouslyProvider`](./index.md)<br>The implementation of the [ContinuouslyProvider](./index.md) interface. |

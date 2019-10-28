[kaspresso](../../index.md) / [com.kaspersky.kaspresso.compose](../index.md) / [ComposeProvider](./index.md)

# ComposeProvider

`interface ComposeProvider`

The interface to provide composing actions and assertions functionality.

### Functions

| Name | Summary |
|---|---|
| [compose](compose.md) | `abstract fun compose(block: `[`ActionsOnElementsPack`](../../com.kaspersky.kaspresso.compose.pack/-actions-on-elements-pack/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Composes a [block](compose.md#com.kaspersky.kaspresso.compose.ComposeProvider$compose(kotlin.Function1((com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack, kotlin.Unit)))/block) of actions with their views to invoke on in one composite action that succeeds if at least one of it's parts succeeds.`abstract fun <T> `[`T`](compose.md#T)`.compose(block: `[`ActionsPack`](../../com.kaspersky.kaspresso.compose.pack/-actions-pack/index.md)`<`[`T`](compose.md#T)`>.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)` where T : BaseActions, T : BaseAssertions, T : Interceptable<ViewInteraction, ViewAssertion, ViewAction>`<br>Composes a [block](compose.md#com.kaspersky.kaspresso.compose.ComposeProvider$compose(com.kaspersky.kaspresso.compose.ComposeProvider.compose.T, kotlin.Function1((com.kaspersky.kaspresso.compose.pack.ActionsPack((com.kaspersky.kaspresso.compose.ComposeProvider.compose.T)), kotlin.Unit)))/block) of actions on the given view of type [T](compose.md#T) in one composite action that succeeds if at least one of it's parts succeeds. |

### Inheritors

| Name | Summary |
|---|---|
| [BaseTestContext](../../com.kaspersky.kaspresso.testcases.core.testcontext/-base-test-context/index.md) | `open class BaseTestContext : `[`FlakySafetyProvider`](../../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider/index.md)`, `[`ContinuouslyProvider`](../../com.kaspersky.kaspresso.flakysafety/-continuously-provider/index.md)`, `[`ComposeProvider`](./index.md)`, `[`WebComposeProvider`](../-web-compose-provider/index.md)<br>Provides the Kaspresso functionality for "run" section: [Device](../../com.kaspersky.kaspresso.device/-device/index.md), [AdbServer](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md), the [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md) implementation for external developers. Also provides flaky safety, composing and web composing functionalities via implementing [FlakySafetyProvider](../../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider/index.md), [ComposeProvider](./index.md) and [WebComposeProvider](../-web-compose-provider/index.md) interfaces. |
| [ComposeProviderImpl](../-compose-provider-impl/index.md) | `class ComposeProviderImpl : `[`ComposeProvider`](./index.md)<br>The implementation of the [ComposeProvider](./index.md) interface. |

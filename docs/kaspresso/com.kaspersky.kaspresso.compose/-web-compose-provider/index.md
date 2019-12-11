[kaspresso](../../index.md) / [com.kaspersky.kaspresso.compose](../index.md) / [WebComposeProvider](./index.md)

# WebComposeProvider

`interface WebComposeProvider`

The interface to provide the composing actions and assertions on web views functionality.

### Functions

| Name | Summary |
|---|---|
| [compose](compose.md) | `abstract fun WebElementBuilder.compose(block: `[`ActionsOnWebElementsPack`](../../com.kaspersky.kaspresso.compose.pack/-actions-on-web-elements-pack/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Composes a [block](compose.md#com.kaspersky.kaspresso.compose.WebComposeProvider$compose(com.agoda.kakao.web.WebElementBuilder, kotlin.Function1((com.kaspersky.kaspresso.compose.pack.ActionsOnWebElementsPack, kotlin.Unit)))/block) of actions with their web views to invoke on in one composite action that succeeds if at least one of it's parts succeeds.`abstract fun KWebInteraction.compose(webElementBuilder: WebElementBuilder, block: `[`ActionsPack`](../../com.kaspersky.kaspresso.compose.pack/-actions-pack/index.md)`<KWebInteraction>.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Composes a [block](compose.md#com.kaspersky.kaspresso.compose.WebComposeProvider$compose(com.agoda.kakao.web.WebElementBuilder.KWebInteraction, com.agoda.kakao.web.WebElementBuilder, kotlin.Function1((com.kaspersky.kaspresso.compose.pack.ActionsPack((com.agoda.kakao.web.WebElementBuilder.KWebInteraction)), kotlin.Unit)))/block) of actions on the given web view in one composite action that succeeds if at least one of it's parts succeeds. |

### Inheritors

| Name | Summary |
|---|---|
| [BaseTestContext](../../com.kaspersky.kaspresso.testcases.core.testcontext/-base-test-context.md) | `open class BaseTestContext : `[`FlakySafetyProvider`](../../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider/index.md)`, `[`ContinuouslyProvider`](../../com.kaspersky.kaspresso.flakysafety/-continuously-provider/index.md)`, `[`ComposeProvider`](../-compose-provider/index.md)`, `[`WebComposeProvider`](./index.md)`, `[`TestAssistantsProvider`](../../com.kaspersky.kaspresso.testcases.core.testassistants/-test-assistants-provider/index.md)<br>Provides the Kaspresso functionality for "run" section: [Device](../../com.kaspersky.kaspresso.device/-device/index.md), [AdbServer](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md), the [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md) implementation for external developers. Also provides flaky safety, composing and web composing functionalities via implementing [FlakySafetyProvider](../../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider/index.md), [ComposeProvider](../-compose-provider/index.md) and [WebComposeProvider](./index.md) interfaces. |
| [WebComposeProviderImpl](../-web-compose-provider-impl/index.md) | `class WebComposeProviderImpl : `[`WebComposeProvider`](./index.md)<br>The implementation of the [WebComposeProvider](./index.md) interface. |

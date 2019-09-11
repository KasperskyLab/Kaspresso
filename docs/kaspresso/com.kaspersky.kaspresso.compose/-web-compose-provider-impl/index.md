[kaspresso](../../index.md) / [com.kaspersky.kaspresso.compose](../index.md) / [WebComposeProviderImpl](./index.md)

# WebComposeProviderImpl

`class WebComposeProviderImpl : `[`WebComposeProvider`](../-web-compose-provider/index.md)

The implementation of the [WebComposeProvider](../-web-compose-provider/index.md) interface.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `WebComposeProviderImpl(kaspresso: `[`Kaspresso`](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/index.md)`)`<br>The implementation of the [WebComposeProvider](../-web-compose-provider/index.md) interface. |

### Functions

| Name | Summary |
|---|---|
| [compose](compose.md) | `fun WebElementBuilder.compose(block: `[`ActionsOnWebElementsPack`](../../com.kaspersky.kaspresso.compose.pack/-actions-on-web-elements-pack/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Composes a [block](compose.md#com.kaspersky.kaspresso.compose.WebComposeProviderImpl$compose(com.agoda.kakao.web.WebElementBuilder, kotlin.Function1((com.kaspersky.kaspresso.compose.pack.ActionsOnWebElementsPack, kotlin.Unit)))/block) of actions with their web views to invoke on in one composite action that succeeds if at least one of it's parts succeeds.`fun KWebInteraction.compose(webElementBuilder: WebElementBuilder, block: `[`ActionsPack`](../../com.kaspersky.kaspresso.compose.pack/-actions-pack/index.md)`<KWebInteraction>.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Composes a [block](compose.md#com.kaspersky.kaspresso.compose.WebComposeProviderImpl$compose(com.agoda.kakao.web.WebElementBuilder.KWebInteraction, com.agoda.kakao.web.WebElementBuilder, kotlin.Function1((com.kaspersky.kaspresso.compose.pack.ActionsPack((com.agoda.kakao.web.WebElementBuilder.KWebInteraction)), kotlin.Unit)))/block) of actions on the given web view in one composite action that succeeds if at least one of it's parts succeeds. |

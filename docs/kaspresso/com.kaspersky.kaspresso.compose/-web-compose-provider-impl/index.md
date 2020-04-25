[kaspresso](../../index.md) / [com.kaspersky.kaspresso.compose](../index.md) / [WebComposeProviderImpl](./index.md)

# WebComposeProviderImpl

`class WebComposeProviderImpl : `[`WebComposeProvider`](../-web-compose-provider/index.md)

The implementation of the [WebComposeProvider](../-web-compose-provider/index.md) interface.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The implementation of the [WebComposeProvider](../-web-compose-provider/index.md) interface.`WebComposeProviderImpl(kaspresso: `[`Kaspresso`](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/index.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [compose](compose.md) | Composes a [block](../-web-compose-provider/compose.md#com.kaspersky.kaspresso.compose.WebComposeProvider$compose(com.agoda.kakao.web.WebElementBuilder, kotlin.Long, kotlin.Long, kotlin.collections.Set((java.lang.Class((kotlin.Throwable)))), kotlin.Function1((com.kaspersky.kaspresso.compose.pack.ActionsOnWebElementsPack, kotlin.Unit)))/block) of actions with their web views to invoke on in one composite action that succeeds if at least one of it's parts succeeds. Please, be aware of `or` sections are executing flakySafely!`fun WebElementBuilder.compose(timeoutMs: Long?, intervalMs: Long?, allowedExceptions: Set<Class<out Throwable>>?, block: `[`ActionsOnWebElementsPack`](../../com.kaspersky.kaspresso.compose.pack/-actions-on-web-elements-pack/index.md)`.() -> Unit): Unit`<br>`fun KWebInteraction.compose(webElementBuilder: WebElementBuilder, timeoutMs: Long?, intervalMs: Long?, allowedExceptions: Set<Class<out Throwable>>?, block: `[`ActionsPack`](../../com.kaspersky.kaspresso.compose.pack/-actions-pack/index.md)`<KWebInteraction>.() -> Unit): Unit` |
| [unsafeCompose](unsafe-compose.md) | Composes a [block](../-web-compose-provider/unsafe-compose.md#com.kaspersky.kaspresso.compose.WebComposeProvider$unsafeCompose(com.agoda.kakao.web.WebElementBuilder, kotlin.Function1((com.kaspersky.kaspresso.compose.pack.ActionsOnWebElementsPack, kotlin.Unit)))/block) of actions with their web views to invoke on in one composite action that succeeds if at least one of it's parts succeeds. Please, be aware of `or` sections are executing without flakySafely mechanism     even though there may be flakySafely interceptors in your Kaspresso settings!`fun WebElementBuilder.unsafeCompose(block: `[`ActionsOnWebElementsPack`](../../com.kaspersky.kaspresso.compose.pack/-actions-on-web-elements-pack/index.md)`.() -> Unit): Unit`<br>`fun KWebInteraction.unsafeCompose(webElementBuilder: WebElementBuilder, block: `[`ActionsPack`](../../com.kaspersky.kaspresso.compose.pack/-actions-pack/index.md)`<KWebInteraction>.() -> Unit): Unit` |

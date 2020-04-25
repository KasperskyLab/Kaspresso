[kaspresso](../../index.md) / [com.kaspersky.kaspresso.compose](../index.md) / [WebComposeProviderImpl](index.md) / [unsafeCompose](./unsafe-compose.md)

# unsafeCompose

`fun WebElementBuilder.unsafeCompose(block: `[`ActionsOnWebElementsPack`](../../com.kaspersky.kaspresso.compose.pack/-actions-on-web-elements-pack/index.md)`.() -> Unit): Unit`
`fun KWebInteraction.unsafeCompose(webElementBuilder: WebElementBuilder, block: `[`ActionsPack`](../../com.kaspersky.kaspresso.compose.pack/-actions-pack/index.md)`<KWebInteraction>.() -> Unit): Unit`

Composes a [block](../-web-compose-provider/unsafe-compose.md#com.kaspersky.kaspresso.compose.WebComposeProvider$unsafeCompose(com.agoda.kakao.web.WebElementBuilder, kotlin.Function1((com.kaspersky.kaspresso.compose.pack.ActionsOnWebElementsPack, kotlin.Unit)))/block) of actions with their web views to invoke on in one composite action that succeeds if at least
one of it's parts succeeds.
Please, be aware of `or` sections are executing without flakySafely mechanism
    even though there may be flakySafely interceptors in your Kaspresso settings!

### Parameters

`block` - the actions to compose.
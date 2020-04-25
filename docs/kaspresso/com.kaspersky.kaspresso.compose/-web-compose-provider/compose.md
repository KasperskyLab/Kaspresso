[kaspresso](../../index.md) / [com.kaspersky.kaspresso.compose](../index.md) / [WebComposeProvider](index.md) / [compose](./compose.md)

# compose

`abstract fun WebElementBuilder.compose(timeoutMs: Long? = null, intervalMs: Long? = null, allowedExceptions: Set<Class<out Throwable>>? = null, block: `[`ActionsOnWebElementsPack`](../../com.kaspersky.kaspresso.compose.pack/-actions-on-web-elements-pack/index.md)`.() -> Unit): Unit`
`abstract fun KWebInteraction.compose(webElementBuilder: WebElementBuilder, timeoutMs: Long? = null, intervalMs: Long? = null, allowedExceptions: Set<Class<out Throwable>>? = null, block: `[`ActionsPack`](../../com.kaspersky.kaspresso.compose.pack/-actions-pack/index.md)`<KWebInteraction>.() -> Unit): Unit`

Composes a [block](compose.md#com.kaspersky.kaspresso.compose.WebComposeProvider$compose(com.agoda.kakao.web.WebElementBuilder, kotlin.Long, kotlin.Long, kotlin.collections.Set((java.lang.Class((kotlin.Throwable)))), kotlin.Function1((com.kaspersky.kaspresso.compose.pack.ActionsOnWebElementsPack, kotlin.Unit)))/block) of actions with their web views to invoke on in one composite action that succeeds if at least
one of it's parts succeeds.
Please, be aware of `or` sections are executing flakySafely!

### Parameters

`timeoutMs` - the timeout during which attempts to execute `or` sections will be made.

`intervalMs` - the interval at which attempts to execute `or` sections will be made.

`allowedExceptions` - the set of exceptions that allow to continue an attempt of `or` sections execution.

`block` - the actions to compose.
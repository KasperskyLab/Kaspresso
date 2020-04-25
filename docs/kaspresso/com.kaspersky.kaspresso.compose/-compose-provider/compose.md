[kaspresso](../../index.md) / [com.kaspersky.kaspresso.compose](../index.md) / [ComposeProvider](index.md) / [compose](./compose.md)

# compose

`abstract fun compose(timeoutMs: Long? = null, intervalMs: Long? = null, allowedExceptions: Set<Class<out Throwable>>? = null, block: `[`ActionsOnElementsPack`](../../com.kaspersky.kaspresso.compose.pack/-actions-on-elements-pack/index.md)`.() -> Unit): Unit`

Composes a [block](compose.md#com.kaspersky.kaspresso.compose.ComposeProvider$compose(kotlin.Long, kotlin.Long, kotlin.collections.Set((java.lang.Class((kotlin.Throwable)))), kotlin.Function1((com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack, kotlin.Unit)))/block) of actions with their views to invoke on in one composite action that succeeds if at least
one of it's parts succeeds.
Please, be aware of `or` sections are executing flakySafely!

### Parameters

`timeoutMs` - the timeout during which attempts to execute `or` sections will be made.

`intervalMs` - the interval at which attempts to execute `or` sections will be made.

`allowedExceptions` - the set of exceptions that allow to continue an attempt of `or` sections execution.

`block` - the actions to compose.`abstract fun <Type> Type.compose(timeoutMs: Long? = null, intervalMs: Long? = null, allowedExceptions: Set<Class<out Throwable>>? = null, block: `[`ActionsPack`](../../com.kaspersky.kaspresso.compose.pack/-actions-pack/index.md)`<Type>.() -> Unit): Unit where Type : BaseActions, Type : BaseAssertions, Type : Interceptable<ViewInteraction, ViewAssertion, ViewAction>`
`abstract fun <Type> Type.compose(timeoutMs: Long? = null, intervalMs: Long? = null, allowedExceptions: Set<Class<out Throwable>>? = null, block: `[`ActionsPack`](../../com.kaspersky.kaspresso.compose.pack/-actions-pack/index.md)`<Type>.() -> Unit): Unit where Type : UiBaseActions, Type : UiBaseAssertions, Type : UiInterceptable<UiObjectInteraction, UiObjectAssertion, UiObjectAction>`

Composes a [block](compose.md#com.kaspersky.kaspresso.compose.ComposeProvider$compose(com.kaspersky.kaspresso.compose.ComposeProvider.compose.Type, kotlin.Long, kotlin.Long, kotlin.collections.Set((java.lang.Class((kotlin.Throwable)))), kotlin.Function1((com.kaspersky.kaspresso.compose.pack.ActionsPack((com.kaspersky.kaspresso.compose.ComposeProvider.compose.Type)), kotlin.Unit)))/block) of actions on the given view of type [Type](compose.md#Type) in one composite action that succeeds if at least
one of it's parts succeeds.
Please, be aware of `or` sections are executing flakySafely!

### Parameters

`timeoutMs` - the timeout during which attempts to execute `or` sections will be made.

`intervalMs` - the interval at which attempts to execute `or` sections will be made.

`allowedExceptions` - the set of exceptions that allow to continue an attempt of `or` sections execution.

`block` - the actions to compose.
[kaspresso](../../index.md) / [com.kaspersky.kaspresso.compose](../index.md) / [ComposeProviderImpl](index.md) / [compose](./compose.md)

# compose

`fun compose(timeoutMs: Long?, intervalMs: Long?, allowedExceptions: Set<Class<out Throwable>>?, block: `[`ActionsOnElementsPack`](../../com.kaspersky.kaspresso.compose.pack/-actions-on-elements-pack/index.md)`.() -> Unit): Unit`

Composes a [block](../-compose-provider/compose.md#com.kaspersky.kaspresso.compose.ComposeProvider$compose(kotlin.Long, kotlin.Long, kotlin.collections.Set((java.lang.Class((kotlin.Throwable)))), kotlin.Function1((com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack, kotlin.Unit)))/block) of actions with their views to invoke on in one composite action that succeeds if at least
one of it's parts succeeds.
Please, be aware of `or` sections are executing flakySafely!

### Parameters

`timeoutMs` - the timeout during which attempts to execute `or` sections will be made.

`intervalMs` - the interval at which attempts to execute `or` sections will be made.

`allowedExceptions` - the set of exceptions that allow to continue an attempt of `or` sections execution.

`block` - the actions to compose.`fun <Type> Type.compose(timeoutMs: Long?, intervalMs: Long?, allowedExceptions: Set<Class<out Throwable>>?, block: `[`ActionsPack`](../../com.kaspersky.kaspresso.compose.pack/-actions-pack/index.md)`<Type>.() -> Unit): Unit where Type : BaseActions, Type : BaseAssertions, Type : Interceptable<ViewInteraction, ViewAssertion, ViewAction>`
`fun <Type> Type.compose(timeoutMs: Long?, intervalMs: Long?, allowedExceptions: Set<Class<out Throwable>>?, block: `[`ActionsPack`](../../com.kaspersky.kaspresso.compose.pack/-actions-pack/index.md)`<Type>.() -> Unit): Unit where Type : UiBaseActions, Type : UiBaseAssertions, Type : UiInterceptable<UiObjectInteraction, UiObjectAssertion, UiObjectAction>`

Composes a [block](../-compose-provider/compose.md#com.kaspersky.kaspresso.compose.ComposeProvider$compose(com.kaspersky.kaspresso.compose.ComposeProvider.compose.Type, kotlin.Long, kotlin.Long, kotlin.collections.Set((java.lang.Class((kotlin.Throwable)))), kotlin.Function1((com.kaspersky.kaspresso.compose.pack.ActionsPack((com.kaspersky.kaspresso.compose.ComposeProvider.compose.Type)), kotlin.Unit)))/block) of actions on the given view of type [Type](../-compose-provider/compose.md#Type) in one composite action that succeeds if at least
one of it's parts succeeds.
Please, be aware of `or` sections are executing flakySafely!

### Parameters

`timeoutMs` - the timeout during which attempts to execute `or` sections will be made.

`intervalMs` - the interval at which attempts to execute `or` sections will be made.

`allowedExceptions` - the set of exceptions that allow to continue an attempt of `or` sections execution.

`block` - the actions to compose.
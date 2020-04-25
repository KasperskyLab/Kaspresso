[kaspresso](../../index.md) / [com.kaspersky.kaspresso.autoscroll](../index.md) / [AutoScrollProvider](index.md) / [scroll](./scroll.md)

# scroll

`abstract fun <T> scroll(interaction: Interaction, action: () -> T, cachedError: Throwable): T`

Performs the autoscrolling functionality. Performs scroll and re-invokes the given [action](scroll.md#com.kaspersky.kaspresso.autoscroll.AutoScrollProvider$scroll(com.kaspersky.kaspresso.autoscroll.AutoScrollProvider.Interaction, kotlin.Function0((com.kaspersky.kaspresso.autoscroll.AutoScrollProvider.scroll.T)), kotlin.Throwable)/action).

### Parameters

`interaction` - the interaction interface to perform actions and assertions.

`action` - the actual action on the interacted view.

`cachedError` - the error to be thrown if autoscroll would not help.

**Return**
the result of [action](scroll.md#com.kaspersky.kaspresso.autoscroll.AutoScrollProvider$scroll(com.kaspersky.kaspresso.autoscroll.AutoScrollProvider.Interaction, kotlin.Function0((com.kaspersky.kaspresso.autoscroll.AutoScrollProvider.scroll.T)), kotlin.Throwable)/action) invocation.


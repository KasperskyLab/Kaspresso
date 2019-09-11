[kaspresso](../../index.md) / [com.kaspersky.kaspresso.autoscroll](../index.md) / [AutoScrollProvider](index.md) / [withAutoScroll](./with-auto-scroll.md)

# withAutoScroll

`abstract fun <T> withAutoScroll(interaction: `[`Interaction`](index.md#Interaction)`, action: () -> `[`T`](with-auto-scroll.md#T)`): `[`T`](with-auto-scroll.md#T)

Invokes the given [action](with-auto-scroll.md#com.kaspersky.kaspresso.autoscroll.AutoScrollProvider$withAutoScroll(com.kaspersky.kaspresso.autoscroll.AutoScrollProvider.Interaction, kotlin.Function0((com.kaspersky.kaspresso.autoscroll.AutoScrollProvider.withAutoScroll.T)))/action) and calls [scroll](scroll.md) if it fails. Helps in cases when test fails because of the
need to scroll to interacted view.

### Parameters

`interaction` - the interaction interface to perform actions and assertions.

`action` - the actual action on the interacted view.

**Return**
the result of [action](with-auto-scroll.md#com.kaspersky.kaspresso.autoscroll.AutoScrollProvider$withAutoScroll(com.kaspersky.kaspresso.autoscroll.AutoScrollProvider.Interaction, kotlin.Function0((com.kaspersky.kaspresso.autoscroll.AutoScrollProvider.withAutoScroll.T)))/action) invocation.


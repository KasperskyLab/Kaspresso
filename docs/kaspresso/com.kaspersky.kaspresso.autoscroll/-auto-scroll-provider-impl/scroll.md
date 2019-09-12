[kaspresso](../../index.md) / [com.kaspersky.kaspresso.autoscroll](../index.md) / [AutoScrollProviderImpl](index.md) / [scroll](./scroll.md)

# scroll

`fun <T> scroll(interaction: ViewInteraction, action: () -> `[`T`](scroll.md#T)`, cachedError: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): `[`T`](scroll.md#T)

Overrides [AutoScrollProvider.scroll](../-auto-scroll-provider/scroll.md)

Performs the autoscrolling functionality. Performs scroll and re-invokes the given [action](scroll.md#com.kaspersky.kaspresso.autoscroll.AutoScrollProviderImpl$scroll(android.support.test.espresso.ViewInteraction, kotlin.Function0((com.kaspersky.kaspresso.autoscroll.AutoScrollProviderImpl.scroll.T)), kotlin.Throwable)/action).

### Parameters

`interaction` - the instance of [ViewInteraction](#) interface to perform actions and assertions.

`action` - the actual action on the interacted view.

`cachedError` - the error to be thrown if autoscroll would not help.

### Exceptions

`cachedError` - if autoscroll action did not help.

**Return**
the result of [action](scroll.md#com.kaspersky.kaspresso.autoscroll.AutoScrollProviderImpl$scroll(android.support.test.espresso.ViewInteraction, kotlin.Function0((com.kaspersky.kaspresso.autoscroll.AutoScrollProviderImpl.scroll.T)), kotlin.Throwable)/action) invocation.


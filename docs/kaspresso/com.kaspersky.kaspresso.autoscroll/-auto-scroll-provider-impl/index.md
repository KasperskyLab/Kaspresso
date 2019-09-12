[kaspresso](../../index.md) / [com.kaspersky.kaspresso.autoscroll](../index.md) / [AutoScrollProviderImpl](./index.md)

# AutoScrollProviderImpl

`class AutoScrollProviderImpl : `[`AutoScrollProvider`](../-auto-scroll-provider/index.md)`<ViewInteraction>`

The implementation of the [AutoScrollProvider](../-auto-scroll-provider/index.md) interface for [ViewInteraction](#)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `AutoScrollProviderImpl(params: `[`AutoScrollParams`](../-auto-scroll-params/index.md)`, logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`)`<br>The implementation of the [AutoScrollProvider](../-auto-scroll-provider/index.md) interface for [ViewInteraction](#) |

### Functions

| Name | Summary |
|---|---|
| [scroll](scroll.md) | `fun <T> scroll(interaction: ViewInteraction, action: () -> `[`T`](scroll.md#T)`, cachedError: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): `[`T`](scroll.md#T)<br>Performs the autoscrolling functionality. Performs scroll and re-invokes the given [action](scroll.md#com.kaspersky.kaspresso.autoscroll.AutoScrollProviderImpl$scroll(android.support.test.espresso.ViewInteraction, kotlin.Function0((com.kaspersky.kaspresso.autoscroll.AutoScrollProviderImpl.scroll.T)), kotlin.Throwable)/action). |
| [withAutoScroll](with-auto-scroll.md) | `fun <T> withAutoScroll(interaction: ViewInteraction, action: () -> `[`T`](with-auto-scroll.md#T)`): `[`T`](with-auto-scroll.md#T)<br>Invokes the given [action](with-auto-scroll.md#com.kaspersky.kaspresso.autoscroll.AutoScrollProviderImpl$withAutoScroll(android.support.test.espresso.ViewInteraction, kotlin.Function0((com.kaspersky.kaspresso.autoscroll.AutoScrollProviderImpl.withAutoScroll.T)))/action) and calls [scroll](scroll.md) if it fails. Helps in cases when test fails because of the need to scroll to interacted view. |

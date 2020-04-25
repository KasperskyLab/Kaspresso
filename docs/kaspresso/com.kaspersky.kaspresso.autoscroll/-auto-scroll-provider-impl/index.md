[kaspresso](../../index.md) / [com.kaspersky.kaspresso.autoscroll](../index.md) / [AutoScrollProviderImpl](./index.md)

# AutoScrollProviderImpl

`class AutoScrollProviderImpl : `[`AutoScrollProvider`](../-auto-scroll-provider/index.md)`<ViewInteraction>`

The implementation of the [AutoScrollProvider](../-auto-scroll-provider/index.md) interface for [ViewInteraction](#)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The implementation of the [AutoScrollProvider](../-auto-scroll-provider/index.md) interface for [ViewInteraction](#)`AutoScrollProviderImpl(params: `[`AutoScrollParams`](../../com.kaspersky.kaspresso.params/-auto-scroll-params/index.md)`, logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [scroll](scroll.md) | Performs the autoscrolling functionality. Performs scroll and re-invokes the given [action](scroll.md#com.kaspersky.kaspresso.autoscroll.AutoScrollProviderImpl$scroll(androidx.test.espresso.ViewInteraction, kotlin.Function0((com.kaspersky.kaspresso.autoscroll.AutoScrollProviderImpl.scroll.T)), kotlin.Throwable)/action).`fun <T> scroll(interaction: ViewInteraction, action: () -> T, cachedError: Throwable): T` |
| [withAutoScroll](with-auto-scroll.md) | Invokes the given [action](with-auto-scroll.md#com.kaspersky.kaspresso.autoscroll.AutoScrollProviderImpl$withAutoScroll(androidx.test.espresso.ViewInteraction, kotlin.Function0((com.kaspersky.kaspresso.autoscroll.AutoScrollProviderImpl.withAutoScroll.T)))/action) and calls [scroll](scroll.md) if it fails. Helps in cases when test fails because of the need to scroll to interacted view.`fun <T> withAutoScroll(interaction: ViewInteraction, action: () -> T): T` |

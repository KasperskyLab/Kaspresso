[kaspresso](../../index.md) / [com.kaspersky.kaspresso.autoscroll](../index.md) / [WebAutoScrollProviderImpl](./index.md)

# WebAutoScrollProviderImpl

`class WebAutoScrollProviderImpl : `[`AutoScrollProvider`](../-auto-scroll-provider/index.md)`<WebInteraction<*>>`

The implementation of the [AutoScrollProvider](../-auto-scroll-provider/index.md) interface for [Web.WebInteraction](#)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The implementation of the [AutoScrollProvider](../-auto-scroll-provider/index.md) interface for [Web.WebInteraction](#)`WebAutoScrollProviderImpl(params: `[`AutoScrollParams`](../../com.kaspersky.kaspresso.params/-auto-scroll-params/index.md)`, logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [scroll](scroll.md) | Performs the autoscrolling functionality. Performs scroll and re-invokes the given [action](scroll.md#com.kaspersky.kaspresso.autoscroll.WebAutoScrollProviderImpl$scroll(androidx.test.espresso.web.sugar.Web.WebInteraction((kotlin.Any)), kotlin.Function0((com.kaspersky.kaspresso.autoscroll.WebAutoScrollProviderImpl.scroll.T)), kotlin.Throwable)/action).`fun <T> scroll(interaction: WebInteraction<*>, action: () -> T, cachedError: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): T` |
| [withAutoScroll](with-auto-scroll.md) | Invokes the given [action](with-auto-scroll.md#com.kaspersky.kaspresso.autoscroll.WebAutoScrollProviderImpl$withAutoScroll(androidx.test.espresso.web.sugar.Web.WebInteraction((kotlin.Any)), kotlin.Function0((com.kaspersky.kaspresso.autoscroll.WebAutoScrollProviderImpl.withAutoScroll.T)))/action) and calls [scroll](scroll.md) if it fails. Helps in cases when test fails because of the need to scroll to interacted view.`fun <T> withAutoScroll(interaction: WebInteraction<*>, action: () -> T): T` |

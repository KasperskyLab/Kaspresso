[kaspresso](../../index.md) / [com.kaspersky.kaspresso.autoscroll](../index.md) / [WebAutoScrollProviderImpl](./index.md)

# WebAutoScrollProviderImpl

`class WebAutoScrollProviderImpl : `[`AutoScrollProvider`](../-auto-scroll-provider/index.md)`<WebInteraction<*>>`

The implementation of the [AutoScrollProvider](../-auto-scroll-provider/index.md) interface for [Web.WebInteraction](#)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `WebAutoScrollProviderImpl(params: `[`AutoScrollParams`](../../com.kaspersky.kaspresso.params/-auto-scroll-params/index.md)`, logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`)`<br>The implementation of the [AutoScrollProvider](../-auto-scroll-provider/index.md) interface for [Web.WebInteraction](#) |

### Functions

| Name | Summary |
|---|---|
| [scroll](scroll.md) | `fun <T> scroll(interaction: WebInteraction<*>, action: () -> `[`T`](scroll.md#T)`, cachedError: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): `[`T`](scroll.md#T)<br>Performs the autoscrolling functionality. Performs scroll and re-invokes the given [action](scroll.md#com.kaspersky.kaspresso.autoscroll.WebAutoScrollProviderImpl$scroll(androidx.test.espresso.web.sugar.Web.WebInteraction((kotlin.Any)), kotlin.Function0((com.kaspersky.kaspresso.autoscroll.WebAutoScrollProviderImpl.scroll.T)), kotlin.Throwable)/action). |
| [withAutoScroll](with-auto-scroll.md) | `fun <T> withAutoScroll(interaction: WebInteraction<*>, action: () -> `[`T`](with-auto-scroll.md#T)`): `[`T`](with-auto-scroll.md#T)<br>Invokes the given [action](with-auto-scroll.md#com.kaspersky.kaspresso.autoscroll.WebAutoScrollProviderImpl$withAutoScroll(androidx.test.espresso.web.sugar.Web.WebInteraction((kotlin.Any)), kotlin.Function0((com.kaspersky.kaspresso.autoscroll.WebAutoScrollProviderImpl.withAutoScroll.T)))/action) and calls [scroll](scroll.md) if it fails. Helps in cases when test fails because of the need to scroll to interacted view. |

[kaspresso](../../index.md) / [com.kaspersky.kaspresso.autoscroll](../index.md) / [ObjectAutoScrollProviderImpl](./index.md)

# ObjectAutoScrollProviderImpl

`class ObjectAutoScrollProviderImpl : `[`AutoScrollProvider`](../-auto-scroll-provider/index.md)`<UiObjectInteraction>`

The implementation of the [AutoScrollProvider](../-auto-scroll-provider/index.md) interface for [UiObjectInteraction](#)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The implementation of the [AutoScrollProvider](../-auto-scroll-provider/index.md) interface for [UiObjectInteraction](#)`ObjectAutoScrollProviderImpl(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`, autoScrollParams: `[`AutoScrollParams`](../../com.kaspersky.kaspresso.params/-auto-scroll-params/index.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [scroll](scroll.md) | Performs the autoscrolling functionality. Performs scroll and re-invokes the given [action](scroll.md#com.kaspersky.kaspresso.autoscroll.ObjectAutoScrollProviderImpl$scroll(com.kaspersky.components.kautomator.intercept.interaction.UiObjectInteraction, kotlin.Function0((com.kaspersky.kaspresso.autoscroll.ObjectAutoScrollProviderImpl.scroll.T)), kotlin.Throwable)/action).`fun <T> scroll(interaction: UiObjectInteraction, action: () -> T, cachedError: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): T` |
| [withAutoScroll](with-auto-scroll.md) | Invokes the given [action](with-auto-scroll.md#com.kaspersky.kaspresso.autoscroll.ObjectAutoScrollProviderImpl$withAutoScroll(com.kaspersky.components.kautomator.intercept.interaction.UiObjectInteraction, kotlin.Function0((com.kaspersky.kaspresso.autoscroll.ObjectAutoScrollProviderImpl.withAutoScroll.T)))/action) and calls [scroll](scroll.md) if it fails. Helps in cases when test fails because of the need to scroll to interacted view.`fun <T> withAutoScroll(interaction: UiObjectInteraction, action: () -> T): T` |

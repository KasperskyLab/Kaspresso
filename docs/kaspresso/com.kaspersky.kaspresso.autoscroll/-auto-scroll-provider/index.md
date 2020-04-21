[kaspresso](../../index.md) / [com.kaspersky.kaspresso.autoscroll](../index.md) / [AutoScrollProvider](./index.md)

# AutoScrollProvider

`interface AutoScrollProvider<Interaction>`

The interface to provide autoscroll functionality.

### Functions

| Name | Summary |
|---|---|
| [scroll](scroll.md) | `abstract fun <T> scroll(interaction: `[`Interaction`](index.md#Interaction)`, action: () -> `[`T`](scroll.md#T)`, cachedError: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): `[`T`](scroll.md#T)<br>Performs the autoscrolling functionality. Performs scroll and re-invokes the given [action](scroll.md#com.kaspersky.kaspresso.autoscroll.AutoScrollProvider$scroll(com.kaspersky.kaspresso.autoscroll.AutoScrollProvider.Interaction, kotlin.Function0((com.kaspersky.kaspresso.autoscroll.AutoScrollProvider.scroll.T)), kotlin.Throwable)/action). |
| [withAutoScroll](with-auto-scroll.md) | `abstract fun <T> withAutoScroll(interaction: `[`Interaction`](index.md#Interaction)`, action: () -> `[`T`](with-auto-scroll.md#T)`): `[`T`](with-auto-scroll.md#T)<br>Invokes the given [action](with-auto-scroll.md#com.kaspersky.kaspresso.autoscroll.AutoScrollProvider$withAutoScroll(com.kaspersky.kaspresso.autoscroll.AutoScrollProvider.Interaction, kotlin.Function0((com.kaspersky.kaspresso.autoscroll.AutoScrollProvider.withAutoScroll.T)))/action) and calls [scroll](scroll.md) if it fails. Helps in cases when test fails because of the need to scroll to interacted view. |

### Inheritors

| Name | Summary |
|---|---|
| [AutoScrollObjectBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.autoscroll/-auto-scroll-object-behavior-interceptor/index.md) | `class AutoScrollObjectBehaviorInterceptor : `[`ObjectBehaviorInterceptor`](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-object-behavior-interceptor.md)`, `[`AutoScrollProvider`](./index.md)`<UiObjectInteraction>`<br>The implementation of [ObjectBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-object-behavior-interceptor.md) and [AutoScrollProvider](./index.md) interfaces. Provides autoscroll on failure functionality for [UiObjectInteraction.perform](#) and [UiObjectInteraction.check](#) calls. |
| [AutoScrollProviderImpl](../-auto-scroll-provider-impl/index.md) | `class AutoScrollProviderImpl : `[`AutoScrollProvider`](./index.md)`<ViewInteraction>`<br>The implementation of the [AutoScrollProvider](./index.md) interface for [ViewInteraction](#) |
| [AutoScrollViewBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll/-auto-scroll-view-behavior-interceptor/index.md) | `class AutoScrollViewBehaviorInterceptor : `[`ViewBehaviorInterceptor`](../../com.kaspersky.kaspresso.interceptors.behavior/-view-behavior-interceptor.md)`, `[`AutoScrollProvider`](./index.md)`<ViewInteraction>`<br>The implementation of [ViewBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior/-view-behavior-interceptor.md) and [AutoScrollProvider](./index.md) interfaces. Provides autoscroll on failure functionality for [ViewInteraction.perform](#) and [ViewInteraction.check](#) calls. |
| [AutoScrollWebBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll/-auto-scroll-web-behavior-interceptor/index.md) | `class AutoScrollWebBehaviorInterceptor : `[`WebBehaviorInterceptor`](../../com.kaspersky.kaspresso.interceptors.behavior/-web-behavior-interceptor.md)`, `[`AutoScrollProvider`](./index.md)`<WebInteraction<*>>`<br>The implementation of [WebBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior/-web-behavior-interceptor.md) and [AutoScrollProvider](./index.md) interfaces. Provides autoscroll on failure functionality for [Web.WebInteraction.perform](#) and [Web.WebInteraction.check](#) calls. |
| [ObjectAutoScrollProviderImpl](../-object-auto-scroll-provider-impl/index.md) | `class ObjectAutoScrollProviderImpl : `[`AutoScrollProvider`](./index.md)`<UiObjectInteraction>`<br>The implementation of the [AutoScrollProvider](./index.md) interface for [UiObjectInteraction](#) |
| [WebAutoScrollProviderImpl](../-web-auto-scroll-provider-impl/index.md) | `class WebAutoScrollProviderImpl : `[`AutoScrollProvider`](./index.md)`<WebInteraction<*>>`<br>The implementation of the [AutoScrollProvider](./index.md) interface for [Web.WebInteraction](#) |

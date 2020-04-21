[kaspresso](../../index.md) / [com.kaspersky.kaspresso.autoscroll](../index.md) / [ObjectAutoScrollProviderImpl](index.md) / [withAutoScroll](./with-auto-scroll.md)

# withAutoScroll

`fun <T> withAutoScroll(interaction: UiObjectInteraction, action: () -> `[`T`](with-auto-scroll.md#T)`): `[`T`](with-auto-scroll.md#T)

Overrides [AutoScrollProvider.withAutoScroll](../-auto-scroll-provider/with-auto-scroll.md)

Invokes the given [action](with-auto-scroll.md#com.kaspersky.kaspresso.autoscroll.ObjectAutoScrollProviderImpl$withAutoScroll(com.kaspersky.components.kautomator.intercept.interaction.UiObjectInteraction, kotlin.Function0((com.kaspersky.kaspresso.autoscroll.ObjectAutoScrollProviderImpl.withAutoScroll.T)))/action) and calls [scroll](scroll.md) if it fails. Helps in cases when test fails because of the
need to scroll to interacted view.

### Parameters

`interaction` - the instance of [UiObjectInteraction](#) interface to perform actions and assertions.

`action` - the actual action on the interacted view.

### Exceptions

`Throwable` - if the exception caught while invoking [action](with-auto-scroll.md#com.kaspersky.kaspresso.autoscroll.ObjectAutoScrollProviderImpl$withAutoScroll(com.kaspersky.components.kautomator.intercept.interaction.UiObjectInteraction, kotlin.Function0((com.kaspersky.kaspresso.autoscroll.ObjectAutoScrollProviderImpl.withAutoScroll.T)))/action) is not allowed via [ALLOWED_EXCEPTIONS](#).

**Return**
the result of [action](with-auto-scroll.md#com.kaspersky.kaspresso.autoscroll.ObjectAutoScrollProviderImpl$withAutoScroll(com.kaspersky.components.kautomator.intercept.interaction.UiObjectInteraction, kotlin.Function0((com.kaspersky.kaspresso.autoscroll.ObjectAutoScrollProviderImpl.withAutoScroll.T)))/action) invocation.


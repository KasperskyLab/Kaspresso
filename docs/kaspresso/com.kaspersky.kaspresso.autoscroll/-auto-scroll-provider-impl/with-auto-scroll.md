[kaspresso](../../index.md) / [com.kaspersky.kaspresso.autoscroll](../index.md) / [AutoScrollProviderImpl](index.md) / [withAutoScroll](./with-auto-scroll.md)

# withAutoScroll

`fun <T> withAutoScroll(interaction: ViewInteraction, action: () -> T): T`

Invokes the given [action](with-auto-scroll.md#com.kaspersky.kaspresso.autoscroll.AutoScrollProviderImpl$withAutoScroll(androidx.test.espresso.ViewInteraction, kotlin.Function0((com.kaspersky.kaspresso.autoscroll.AutoScrollProviderImpl.withAutoScroll.T)))/action) and calls [scroll](scroll.md) if it fails. Helps in cases when test fails because of the
need to scroll to interacted view.

### Parameters

`interaction` - the instance of [ViewInteraction](#) interface to perform actions and assertions.

`action` - the actual action on the interacted view.

### Exceptions

`Throwable` - if the exception caught while invoking [action](with-auto-scroll.md#com.kaspersky.kaspresso.autoscroll.AutoScrollProviderImpl$withAutoScroll(androidx.test.espresso.ViewInteraction, kotlin.Function0((com.kaspersky.kaspresso.autoscroll.AutoScrollProviderImpl.withAutoScroll.T)))/action) is not allowed via [params](#).

**Return**
the result of [action](with-auto-scroll.md#com.kaspersky.kaspresso.autoscroll.AutoScrollProviderImpl$withAutoScroll(androidx.test.espresso.ViewInteraction, kotlin.Function0((com.kaspersky.kaspresso.autoscroll.AutoScrollProviderImpl.withAutoScroll.T)))/action) invocation.


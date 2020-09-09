[kaspresso](../../index.md) / [com.kaspersky.kaspresso.autoscroll](../index.md) / [WebAutoScrollProviderImpl](index.md) / [withAutoScroll](./with-auto-scroll.md)

# withAutoScroll

`fun <T> withAutoScroll(interaction: WebInteraction<*>, action: () -> T): T`

Invokes the given [action](with-auto-scroll.md#com.kaspersky.kaspresso.autoscroll.WebAutoScrollProviderImpl$withAutoScroll(androidx.test.espresso.web.sugar.Web.WebInteraction((kotlin.Any)), kotlin.Function0((com.kaspersky.kaspresso.autoscroll.WebAutoScrollProviderImpl.withAutoScroll.T)))/action) and calls [scroll](scroll.md) if it fails. Helps in cases when test fails because of the
need to scroll to interacted view.

### Parameters

`interaction` - the instance of [Web.WebInteraction](#) interface to perform actions and assertions.

`action` - the actual action on the interacted view.

### Exceptions

`Throwable` - if the exception caught while invoking [action](with-auto-scroll.md#com.kaspersky.kaspresso.autoscroll.WebAutoScrollProviderImpl$withAutoScroll(androidx.test.espresso.web.sugar.Web.WebInteraction((kotlin.Any)), kotlin.Function0((com.kaspersky.kaspresso.autoscroll.WebAutoScrollProviderImpl.withAutoScroll.T)))/action) is not allowed via [params](#).

**Return**
the result of [action](with-auto-scroll.md#com.kaspersky.kaspresso.autoscroll.WebAutoScrollProviderImpl$withAutoScroll(androidx.test.espresso.web.sugar.Web.WebInteraction((kotlin.Any)), kotlin.Function0((com.kaspersky.kaspresso.autoscroll.WebAutoScrollProviderImpl.withAutoScroll.T)))/action) invocation.


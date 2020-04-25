[kaspresso](../../index.md) / [com.kaspersky.kaspresso.autoscroll](../index.md) / [WebAutoScrollProviderImpl](index.md) / [scroll](./scroll.md)

# scroll

`fun <T> scroll(interaction: WebInteraction<*>, action: () -> T, cachedError: Throwable): T`

Performs the autoscrolling functionality. Performs scroll and re-invokes the given [action](scroll.md#com.kaspersky.kaspresso.autoscroll.WebAutoScrollProviderImpl$scroll(androidx.test.espresso.web.sugar.Web.WebInteraction((kotlin.Any)), kotlin.Function0((com.kaspersky.kaspresso.autoscroll.WebAutoScrollProviderImpl.scroll.T)), kotlin.Throwable)/action).

### Parameters

`interaction` - the instance of [Web.WebInteraction](#) interface to perform actions and assertions.

`action` - the actual action on the interacted view.

`cachedError` - the error to be thrown if autoscroll would not help.

### Exceptions

`cachedError` - if autoscroll action did not help.

**Return**
the result of [action](scroll.md#com.kaspersky.kaspresso.autoscroll.WebAutoScrollProviderImpl$scroll(androidx.test.espresso.web.sugar.Web.WebInteraction((kotlin.Any)), kotlin.Function0((com.kaspersky.kaspresso.autoscroll.WebAutoScrollProviderImpl.scroll.T)), kotlin.Throwable)/action) invocation.


[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.view.impl.logging](../index.md) / [LoggingViewAssertionWatcherInterceptor](index.md) / [intercept](./intercept.md)

# intercept

`fun intercept(viewAssertion: ViewAssertion, view: View?, exception: NoMatchingViewException?): Unit`

Writes info to [logger](#).

### Parameters

`viewAssertion` - responsible for performing assertions on a [View](#) element.

`view` - an Android [View](#), on which [viewAssertion](intercept.md#com.kaspersky.kaspresso.interceptors.watcher.view.impl.logging.LoggingViewAssertionWatcherInterceptor$intercept(androidx.test.espresso.ViewAssertion, android.view.View, androidx.test.espresso.NoMatchingViewException)/viewAssertion) is performed.

`exception` - indicates that a given matcher did not match any elements in the view hierarchy.
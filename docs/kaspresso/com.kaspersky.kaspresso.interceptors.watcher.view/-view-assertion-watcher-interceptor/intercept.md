[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.view](../index.md) / [ViewAssertionWatcherInterceptor](index.md) / [intercept](./intercept.md)

# intercept

`abstract fun intercept(viewAssertion: ViewAssertion, view: View?, exception: NoMatchingViewException?): Unit`

Called to do some stuff before [androidx.test.espresso.ViewAssertion.check](#) is actually called.

### Parameters

`viewAssertion` - responsible for performing assertions on a [View](#) element.

`view` - an Android [View](#), on which [viewAssertion](intercept.md#com.kaspersky.kaspresso.interceptors.watcher.view.ViewAssertionWatcherInterceptor$intercept(androidx.test.espresso.ViewAssertion, android.view.View, androidx.test.espresso.NoMatchingViewException)/viewAssertion) is performed.

`exception` - indicates that a given matcher did not match any elements in the view hierarchy.
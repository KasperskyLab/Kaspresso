[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.view.impl.logging](../index.md) / [LoggingViewActionWatcherInterceptor](index.md) / [intercept](./intercept.md)

# intercept

`fun intercept(viewAction: ViewAction, view: `[`View`](https://developer.android.com/reference/android/view/View.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Writes info to [logger](#).

### Parameters

`viewAction` - responsible for performing an interaction on the given [View](https://developer.android.com/reference/android/view/View.html) element.

`view` - an Android [View](https://developer.android.com/reference/android/view/View.html), on which [viewAction](intercept.md#com.kaspersky.kaspresso.interceptors.watcher.view.impl.logging.LoggingViewActionWatcherInterceptor$intercept(androidx.test.espresso.ViewAction, android.view.View)/viewAction) is performed.
[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.view](../index.md) / [ViewActionWatcherInterceptor](index.md) / [intercept](./intercept.md)

# intercept

`abstract fun intercept(viewAction: ViewAction, view: `[`View`](https://developer.android.com/reference/android/view/View.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Called to do some stuff before [ViewAction.perform](#) is actually called.

### Parameters

`viewAction` - responsible for performing an interaction on the given [View](https://developer.android.com/reference/android/view/View.html) element.

`view` - an Android [View](https://developer.android.com/reference/android/view/View.html), on which [viewAction](intercept.md#com.kaspersky.kaspresso.interceptors.watcher.view.ViewActionWatcherInterceptor$intercept(android.support.test.espresso.ViewAction, android.view.View)/viewAction) is performed.
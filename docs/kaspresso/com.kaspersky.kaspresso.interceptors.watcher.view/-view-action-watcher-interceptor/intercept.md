[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.view](../index.md) / [ViewActionWatcherInterceptor](index.md) / [intercept](./intercept.md)

# intercept

`abstract fun intercept(viewAction: ViewAction, view: View): Unit`

Called to do some stuff before [ViewAction.perform](#) is actually called.

### Parameters

`viewAction` - responsible for performing an interaction on the given [View](#) element.

`view` - an Android [View](#), on which [viewAction](intercept.md#com.kaspersky.kaspresso.interceptors.watcher.view.ViewActionWatcherInterceptor$intercept(androidx.test.espresso.ViewAction, android.view.View)/viewAction) is performed.
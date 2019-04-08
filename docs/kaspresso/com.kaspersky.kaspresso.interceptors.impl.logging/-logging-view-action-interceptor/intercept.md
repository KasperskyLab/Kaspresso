[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.impl.logging](../index.md) / [LoggingViewActionInterceptor](index.md) / [intercept](./intercept.md)

# intercept

`fun intercept(viewAction: ViewAction, view: `[`View`](https://developer.android.com/reference/android/view/View.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Overrides [ViewActionInterceptor.intercept](../../com.kaspersky.kaspresso.interceptors/-view-action-interceptor/intercept.md)

Writes info to [logger](#).

### Parameters

`viewAction` - responsible for performing an interaction on the given [View](https://developer.android.com/reference/android/view/View.html) element.

`view` - an Android [View](https://developer.android.com/reference/android/view/View.html), on which [viewAction](intercept.md#com.kaspersky.kaspresso.interceptors.impl.logging.LoggingViewActionInterceptor$intercept(android.support.test.espresso.ViewAction, android.view.View)/viewAction) is performed.
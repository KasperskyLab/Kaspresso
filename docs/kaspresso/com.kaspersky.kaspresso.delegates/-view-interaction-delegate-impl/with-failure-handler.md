[kaspresso](../../index.md) / [com.kaspersky.kaspresso.delegates](../index.md) / [ViewInteractionDelegateImpl](index.md) / [withFailureHandler](./with-failure-handler.md)

# withFailureHandler

`open fun withFailureHandler(function: (`[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`, Matcher<`[`View`](https://developer.android.com/reference/android/view/View.html)`>) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): ViewInteractionDelegate`

Calls [ViewInteraction.withFailureHandler](#) on wrapped [viewInteraction](#).

### Parameters

`function` - a function that will be used as [FailureHandler](#).

**Return**
an existing [ViewInteractionDelegateImpl](index.md) instance.


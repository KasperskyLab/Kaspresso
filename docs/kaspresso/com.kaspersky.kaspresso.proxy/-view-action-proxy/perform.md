[kaspresso](../../index.md) / [com.kaspersky.kaspresso.proxy](../index.md) / [ViewActionProxy](index.md) / [perform](./perform.md)

# perform

`fun perform(uiController: UiController, view: `[`View`](https://developer.android.com/reference/android/view/View.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Calls watcher interceptors before [ViewAction.perform](#) on wrapped [viewAction](#) is called.

### Parameters

`uiController` - the controller to use to interact with the UI.

`view` - the view to act upon. never null.
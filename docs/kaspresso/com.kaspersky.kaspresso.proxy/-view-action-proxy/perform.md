[kaspresso](../../index.md) / [com.kaspersky.kaspresso.proxy](../index.md) / [ViewActionProxy](index.md) / [perform](./perform.md)

# perform

`fun perform(uiController: UiController, view: View): Unit`

Calls watcher interceptors before [ViewAction.perform](#) on wrapped [viewAction](#) is called.

### Parameters

`uiController` - the controller to use to interact with the UI.

`view` - the view to act upon. never null.
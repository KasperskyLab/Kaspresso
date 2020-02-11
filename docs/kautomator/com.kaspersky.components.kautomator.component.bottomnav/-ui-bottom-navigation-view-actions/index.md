[kautomator](../../index.md) / [com.kaspersky.components.kautomator.component.bottomnav](../index.md) / [UiBottomNavigationViewActions](./index.md)

# UiBottomNavigationViewActions

`interface UiBottomNavigationViewActions : `[`UiBaseActions`](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/index.md)

Provides actions for BottomNavigationView

### Types

| Name | Summary |
|---|---|
| [UiBottomNavigationViewActionType](-ui-bottom-navigation-view-action-type/index.md) | `enum class UiBottomNavigationViewActionType : `[`UiOperationType`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation-type/index.md) |

### Inherited Properties

| Name | Summary |
|---|---|
| [view](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/view.md) | `abstract val view: `[`UiObjectInteractionDelegate`](../../com.kaspersky.components.kautomator.intercept.delegate/-ui-object-interaction-delegate/index.md) |

### Functions

| Name | Summary |
|---|---|
| [setSelectedItemWithId](set-selected-item-with-id.md) | `open fun setSelectedItemWithId(id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Selects menu item with given id |
| [setSelectedItemWithIndex](set-selected-item-with-index.md) | `open fun setSelectedItemWithIndex(index: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Selects menu item with given index. Note that this method uses view hierarchy which could be changed at any time. |
| [setSelectedItemWithTitle](set-selected-item-with-title.md) | `open fun setSelectedItemWithTitle(title: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Selects menu item with given title. Note that this method uses view hierarchy which could be changed at any time. |

### Inherited Functions

| Name | Summary |
|---|---|
| [click](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/click.md) | `open fun click(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Performs click on view |
| [doubleClick](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/double-click.md) | `open fun doubleClick(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Performs double click on view |
| [longClick](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/long-click.md) | `open fun longClick(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Performs long click on view |

### Inheritors

| Name | Summary |
|---|---|
| [UiBottomNavigationView](../-ui-bottom-navigation-view/index.md) | `class UiBottomNavigationView : `[`UiBaseView`](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)`<`[`UiBottomNavigationView`](../-ui-bottom-navigation-view/index.md)`>, `[`UiBottomNavigationViewActions`](./index.md)`, `[`UiBottomNavigationViewAssertions`](../-ui-bottom-navigation-view-assertions/index.md)<br>View for acting and asserting on BottomNavigationView |

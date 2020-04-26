[kautomator](../../index.md) / [com.kaspersky.components.kautomator.component.bottomnav](../index.md) / [UiBottomNavigationViewActions](./index.md)

# UiBottomNavigationViewActions

`interface UiBottomNavigationViewActions : `[`UiBaseActions`](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/index.md)

Provides actions for BottomNavigationView

### Types

| Name | Summary |
|---|---|
| [UiBottomNavigationViewActionType](-ui-bottom-navigation-view-action-type/index.md) | `enum class UiBottomNavigationViewActionType : `[`UiOperationType`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation-type/index.md) |

### Functions

| Name | Summary |
|---|---|
| [setSelectedItemWithId](set-selected-item-with-id.md) | Selects menu item with given id`open fun setSelectedItemWithId(id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [setSelectedItemWithIndex](set-selected-item-with-index.md) | Selects menu item with given index. Note that this method uses view hierarchy which could be changed at any time.`open fun setSelectedItemWithIndex(index: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [setSelectedItemWithTitle](set-selected-item-with-title.md) | Selects menu item with given title. Note that this method uses view hierarchy which could be changed at any time.`open fun setSelectedItemWithTitle(title: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [UiBottomNavigationView](../-ui-bottom-navigation-view/index.md) | View for acting and asserting on BottomNavigationView`class UiBottomNavigationView : `[`UiBaseView`](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)`<`[`UiBottomNavigationView`](../-ui-bottom-navigation-view/index.md)`>, `[`UiBottomNavigationViewActions`](./index.md)`, `[`UiBottomNavigationViewAssertions`](../-ui-bottom-navigation-view-assertions/index.md) |

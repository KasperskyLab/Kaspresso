[kautomator](../../index.md) / [com.kaspersky.components.kautomator.component.common.actions](../index.md) / [UiBaseActions](./index.md)

# UiBaseActions

`interface UiBaseActions`

Base interface for performing actions on UiAutomator view

Provides a lot of basic action methods, such as click(), etc.

**See Also**

[com.kaspersky.components.kautomator.component.edit.UiEditableActions](../../com.kaspersky.components.kautomator.component.edit/-ui-editable-actions/index.md)

### Types

| Name | Summary |
|---|---|
| [UiBaseActionType](-ui-base-action-type/index.md) | `enum class UiBaseActionType : `[`UiOperationType`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation-type/index.md) |

### Properties

| Name | Summary |
|---|---|
| [view](view.md) | `abstract val view: `[`UiObjectInteractionDelegate`](../../com.kaspersky.components.kautomator.intercept.delegate/-ui-object-interaction-delegate/index.md) |

### Functions

| Name | Summary |
|---|---|
| [click](click.md) | `open fun click(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Performs click on view |
| [doubleClick](double-click.md) | `open fun doubleClick(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Performs double click on view |
| [longClick](long-click.md) | `open fun longClick(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Performs long click on view |

### Inheritors

| Name | Summary |
|---|---|
| [UiBaseView](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md) | `open class UiBaseView<out T> : `[`UiBaseActions`](./index.md)`, `[`UiBaseAssertions`](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/index.md)`, `[`UiInterceptable`](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptable/index.md)`<`[`UiObjectInteraction`](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-object-interaction/index.md)`, `[`UiObjectAssertion`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-object-assertion.md)`, `[`UiObjectAction`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-object-action.md)`>`<br>Base class for all UiAutomator DSL views |
| [UiBottomNavigationViewActions](../../com.kaspersky.components.kautomator.component.bottomnav/-ui-bottom-navigation-view-actions/index.md) | `interface UiBottomNavigationViewActions : `[`UiBaseActions`](./index.md)<br>Provides actions for BottomNavigationView |
| [UiCheckableActions](../../com.kaspersky.components.kautomator.component.check/-ui-checkable-actions/index.md) | `interface UiCheckableActions : `[`UiBaseActions`](./index.md)<br>Provides actions for checkable views |
| [UiChipGroupActions](../../com.kaspersky.components.kautomator.component.chip/-ui-chip-group-actions/index.md) | `interface UiChipGroupActions : `[`UiBaseActions`](./index.md)<br>Provides actions for a ChipGroup |
| [UiEditableActions](../../com.kaspersky.components.kautomator.component.edit/-ui-editable-actions/index.md) | `interface UiEditableActions : `[`UiBaseActions`](./index.md)<br>Provides editable actions for UiEditText |
| [UiScrollableActions](../-ui-scrollable-actions/index.md) | `interface UiScrollableActions : `[`UiBaseActions`](./index.md) |
| [UiSwipeableActions](../-ui-swipeable-actions/index.md) | `interface UiSwipeableActions : `[`UiBaseActions`](./index.md)<br>Provides swipeable actions for UiSwipeView |
| [UiSwitchableActions](../../com.kaspersky.components.kautomator.component.switch/-ui-switchable-actions/index.md) | `interface UiSwitchableActions : `[`UiBaseActions`](./index.md)<br>Provides switchable actions for UiSwitch |

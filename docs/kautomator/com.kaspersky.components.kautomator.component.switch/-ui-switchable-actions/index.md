[kautomator](../../index.md) / [com.kaspersky.components.kautomator.component.switch](../index.md) / [UiSwitchableActions](./index.md)

# UiSwitchableActions

`interface UiSwitchableActions : `[`UiBaseActions`](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/index.md)

Provides switchable actions for UiSwitch

### Types

| Name | Summary |
|---|---|
| [Direction](-direction/index.md) | `enum class Direction` |
| [SwitchableUiActionType](-switchable-ui-action-type/index.md) | `enum class SwitchableUiActionType : `[`UiOperationType`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation-type/index.md) |

### Inherited Properties

| Name | Summary |
|---|---|
| [view](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/view.md) | `abstract val view: `[`UiObjectInteractionDelegate`](../../com.kaspersky.components.kautomator.intercept.delegate/-ui-object-interaction-delegate/index.md) |

### Functions

| Name | Summary |
|---|---|
| [swipeSwitchThumb](swipe-switch-thumb.md) | `open fun swipeSwitchThumb(direction: `[`UiSwitchableActions.Direction`](-direction/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Moves the thumb of the switch to the right |

### Inherited Functions

| Name | Summary |
|---|---|
| [click](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/click.md) | `open fun click(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Performs click on view |
| [doubleClick](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/double-click.md) | `open fun doubleClick(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Performs double click on view |
| [longClick](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/long-click.md) | `open fun longClick(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Performs long click on view |

### Inheritors

| Name | Summary |
|---|---|
| [UiSwitch](../-ui-switch/index.md) | `class UiSwitch : `[`UiBaseView`](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)`<`[`UiSwitch`](../-ui-switch/index.md)`>, `[`UiSwitchableActions`](./index.md)`, `[`UiBaseAssertions`](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/index.md)<br>Ui View with [UiSwitchableActions](./index.md) and [UiBaseAssertions](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/index.md) |

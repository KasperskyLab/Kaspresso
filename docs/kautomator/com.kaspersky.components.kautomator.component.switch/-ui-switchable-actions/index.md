[kautomator](../../index.md) / [com.kaspersky.components.kautomator.component.switch](../index.md) / [UiSwitchableActions](./index.md)

# UiSwitchableActions

`interface UiSwitchableActions : `[`UiBaseActions`](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/index.md)

Provides switchable actions for UiSwitch

### Types

| Name | Summary |
|---|---|
| [Direction](-direction/index.md) | `enum class Direction` |
| [SwitchableUiActionType](-switchable-ui-action-type/index.md) | `enum class SwitchableUiActionType : `[`UiOperationType`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation-type/index.md) |

### Functions

| Name | Summary |
|---|---|
| [swipeSwitchThumb](swipe-switch-thumb.md) | Moves the thumb of the switch to the right`open fun swipeSwitchThumb(direction: Direction): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [UiSwitch](../-ui-switch/index.md) | Ui View with [UiSwitchableActions](./index.md) and [UiBaseAssertions](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/index.md)`class UiSwitch : `[`UiBaseView`](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)`<`[`UiSwitch`](../-ui-switch/index.md)`>, `[`UiSwitchableActions`](./index.md)`, `[`UiBaseAssertions`](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/index.md) |

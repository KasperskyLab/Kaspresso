[kautomator](../../index.md) / [com.kaspersky.components.kautomator.component.check](../index.md) / [UiCheckableActions](./index.md)

# UiCheckableActions

`interface UiCheckableActions : `[`UiBaseActions`](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/index.md)

Provides actions for checkable views

### Types

| Name | Summary |
|---|---|
| [UiCheckableActionType](-ui-checkable-action-type/index.md) | `enum class UiCheckableActionType : `[`UiOperationType`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation-type/index.md) |

### Functions

| Name | Summary |
|---|---|
| [setChecked](set-checked.md) | Sets checked state of the view`open fun setChecked(checked: Boolean): Unit` |

### Inheritors

| Name | Summary |
|---|---|
| [UiCheckBox](../-ui-check-box/index.md) | Ui View with [UiCheckableActions](./index.md) and [UiCheckableAssertions](../-ui-checkable-assertions/index.md)`class UiCheckBox : `[`UiBaseView`](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)`<`[`UiCheckBox`](../-ui-check-box/index.md)`>, `[`UiCheckableActions`](./index.md)`, `[`UiCheckableAssertions`](../-ui-checkable-assertions/index.md) |

[kautomator](../../index.md) / [com.kaspersky.components.kautomator.component.check](../index.md) / [UiCheckableAssertions](./index.md)

# UiCheckableAssertions

`interface UiCheckableAssertions : `[`UiBaseAssertions`](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/index.md)

Provides assertions for checkable views

### Types

| Name | Summary |
|---|---|
| [UiCheckableAssertionType](-ui-checkable-assertion-type/index.md) | `enum class UiCheckableAssertionType : `[`UiOperationType`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation-type/index.md) |

### Functions

| Name | Summary |
|---|---|
| [isChecked](is-checked.md) | Checks if the view is checked`open fun isChecked(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [isNotChecked](is-not-checked.md) | Checks if the view is not checked`open fun isNotChecked(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [UiCheckBox](../-ui-check-box/index.md) | Ui View with [UiCheckableActions](../-ui-checkable-actions/index.md) and [UiCheckableAssertions](./index.md)`class UiCheckBox : `[`UiBaseView`](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)`<`[`UiCheckBox`](../-ui-check-box/index.md)`>, `[`UiCheckableActions`](../-ui-checkable-actions/index.md)`, `[`UiCheckableAssertions`](./index.md) |

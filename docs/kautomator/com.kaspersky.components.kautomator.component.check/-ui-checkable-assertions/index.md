[kautomator](../../index.md) / [com.kaspersky.components.kautomator.component.check](../index.md) / [UiCheckableAssertions](./index.md)

# UiCheckableAssertions

`interface UiCheckableAssertions : `[`UiBaseAssertions`](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/index.md)

Provides assertions for checkable views

### Types

| Name | Summary |
|---|---|
| [UiCheckableAssertionType](-ui-checkable-assertion-type/index.md) | `enum class UiCheckableAssertionType : `[`UiOperationType`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation-type/index.md) |

### Inherited Properties

| Name | Summary |
|---|---|
| [view](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/view.md) | `abstract val view: `[`UiObjectInteractionDelegate`](../../com.kaspersky.components.kautomator.intercept.delegate/-ui-object-interaction-delegate/index.md) |

### Functions

| Name | Summary |
|---|---|
| [isChecked](is-checked.md) | `open fun isChecked(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view is checked |
| [isNotChecked](is-not-checked.md) | `open fun isNotChecked(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view is not checked |

### Inherited Functions

| Name | Summary |
|---|---|
| [isClickable](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-clickable.md) | `open fun isClickable(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view is clickable |
| [isDisabled](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-disabled.md) | `open fun isDisabled(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view is disabled |
| [isDisplayed](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-displayed.md) | `open fun isDisplayed(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view is completely displayed |
| [isEnabled](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-enabled.md) | `open fun isEnabled(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view is enabled |
| [isFocusable](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-focusable.md) | `open fun isFocusable(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view is focusable |
| [isFocused](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-focused.md) | `open fun isFocused(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view is focused |
| [isNotClickable](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-not-clickable.md) | `open fun isNotClickable(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view is not clickable |
| [isNotDisplayed](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-not-displayed.md) | `open fun isNotDisplayed(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view is not completely displayed |
| [isNotFocusable](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-not-focusable.md) | `open fun isNotFocusable(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view is not focusable |
| [isNotFocused](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-not-focused.md) | `open fun isNotFocused(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view is not focused |
| [isNotSelected](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-not-selected.md) | `open fun isNotSelected(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view is not selected |
| [isSelected](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-selected.md) | `open fun isSelected(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view is selected |

### Inheritors

| Name | Summary |
|---|---|
| [UiCheckBox](../-ui-check-box/index.md) | `class UiCheckBox : `[`UiBaseView`](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)`<`[`UiCheckBox`](../-ui-check-box/index.md)`>, `[`UiCheckableActions`](../-ui-checkable-actions/index.md)`, `[`UiCheckableAssertions`](./index.md)<br>Ui View with [UiCheckableActions](../-ui-checkable-actions/index.md) and [UiCheckableAssertions](./index.md) |

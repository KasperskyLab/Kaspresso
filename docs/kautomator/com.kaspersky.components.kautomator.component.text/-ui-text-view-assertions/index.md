[kautomator](../../index.md) / [com.kaspersky.components.kautomator.component.text](../index.md) / [UiTextViewAssertions](./index.md)

# UiTextViewAssertions

`interface UiTextViewAssertions : `[`UiBaseAssertions`](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/index.md)

Provides assertions for UiTextView

### Types

| Name | Summary |
|---|---|
| [UiTextViewAssertionType](-ui-text-view-assertion-type/index.md) | `enum class UiTextViewAssertionType : `[`UiOperationType`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation-type/index.md) |

### Inherited Properties

| Name | Summary |
|---|---|
| [view](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/view.md) | `abstract val view: `[`UiObjectInteractionDelegate`](../../com.kaspersky.components.kautomator.intercept.delegate/-ui-object-interaction-delegate/index.md) |

### Functions

| Name | Summary |
|---|---|
| [containsText](contains-text.md) | `open fun containsText(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view contains concrete text |
| [hasAnyText](has-any-text.md) | `open fun hasAnyText(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view has any text |
| [hasEmptyText](has-empty-text.md) | `open fun hasEmptyText(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view has empty text |
| [hasNoText](has-no-text.md) | `open fun hasNoText(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view has not concrete text |
| [hasText](has-text.md) | `open fun hasText(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view has concrete text |

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
| [UiButton](../-ui-button/index.md) | `class UiButton : `[`UiBaseView`](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)`<`[`UiButton`](../-ui-button/index.md)`>, `[`UiTextViewAssertions`](./index.md)<br>Ui View with UiBaseActions and UiTextViewAssertions |
| [UiEditText](../../com.kaspersky.components.kautomator.component.edit/-ui-edit-text/index.md) | `class UiEditText : `[`UiBaseView`](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)`<`[`UiEditText`](../../com.kaspersky.components.kautomator.component.edit/-ui-edit-text/index.md)`>, `[`UiEditableActions`](../../com.kaspersky.components.kautomator.component.edit/-ui-editable-actions/index.md)`, `[`UiTextViewAssertions`](./index.md)<br>Ui View with [UiEditableActions](../../com.kaspersky.components.kautomator.component.edit/-ui-editable-actions/index.md) and [UiTextViewAssertions](./index.md) |
| [UiTextView](../-ui-text-view/index.md) | `class UiTextView : `[`UiBaseView`](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)`<`[`UiTextView`](../-ui-text-view/index.md)`>, `[`UiBaseAssertions`](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/index.md)`, `[`UiTextViewAssertions`](./index.md)<br>Ui View with UiBaseActions and UiTextViewAssertions |

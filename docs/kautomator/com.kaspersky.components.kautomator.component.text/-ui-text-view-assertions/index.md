[kautomator](../../index.md) / [com.kaspersky.components.kautomator.component.text](../index.md) / [UiTextViewAssertions](./index.md)

# UiTextViewAssertions

`interface UiTextViewAssertions : `[`UiBaseAssertions`](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/index.md)

Provides assertions for UiTextView

### Types

| Name | Summary |
|---|---|
| [UiTextViewAssertionType](-ui-text-view-assertion-type/index.md) | `enum class UiTextViewAssertionType : `[`UiOperationType`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation-type/index.md) |

### Functions

| Name | Summary |
|---|---|
| [containsText](contains-text.md) | Checks if the view contains concrete text`open fun containsText(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [hasAnyText](has-any-text.md) | Checks if the view has any text`open fun hasAnyText(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [hasEmptyText](has-empty-text.md) | Checks if the view has empty text`open fun hasEmptyText(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [hasNoText](has-no-text.md) | Checks if the view has not concrete text`open fun hasNoText(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [hasText](has-text.md) | Checks if the view has concrete text`open fun hasText(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [UiButton](../-ui-button/index.md) | Ui View with UiBaseActions and UiTextViewAssertions`class UiButton : `[`UiBaseView`](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)`<`[`UiButton`](../-ui-button/index.md)`>, `[`UiTextViewAssertions`](./index.md) |
| [UiEditText](../../com.kaspersky.components.kautomator.component.edit/-ui-edit-text/index.md) | Ui View with [UiEditableActions](../../com.kaspersky.components.kautomator.component.edit/-ui-editable-actions/index.md) and [UiTextViewAssertions](./index.md)`class UiEditText : `[`UiBaseView`](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)`<`[`UiEditText`](../../com.kaspersky.components.kautomator.component.edit/-ui-edit-text/index.md)`>, `[`UiEditableActions`](../../com.kaspersky.components.kautomator.component.edit/-ui-editable-actions/index.md)`, `[`UiTextViewAssertions`](./index.md) |
| [UiTextView](../-ui-text-view/index.md) | Ui View with UiBaseActions and UiTextViewAssertions`class UiTextView : `[`UiBaseView`](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)`<`[`UiTextView`](../-ui-text-view/index.md)`>, `[`UiBaseAssertions`](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/index.md)`, `[`UiTextViewAssertions`](./index.md) |

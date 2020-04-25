[kautomator](../../index.md) / [com.kaspersky.components.kautomator.component.edit](../index.md) / [UiEditableActions](./index.md)

# UiEditableActions

`interface UiEditableActions : `[`UiBaseActions`](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/index.md)

Provides editable actions for UiEditText

### Types

| Name | Summary |
|---|---|
| [UiEditableActionType](-ui-editable-action-type/index.md) | `enum class UiEditableActionType : `[`UiOperationType`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation-type/index.md) |

### Functions

| Name | Summary |
|---|---|
| [clearText](clear-text.md) | Clears the text content into the view`open fun clearText(): Unit` |
| [replaceText](replace-text.md) | Replaces the current view text with given`open fun replaceText(text: String): Unit` |
| [typeText](type-text.md) | Types the given text into the view`open fun typeText(text: String): Unit` |

### Inheritors

| Name | Summary |
|---|---|
| [UiEditText](../-ui-edit-text/index.md) | Ui View with [UiEditableActions](./index.md) and [UiTextViewAssertions](../../com.kaspersky.components.kautomator.component.text/-ui-text-view-assertions/index.md)`class UiEditText : `[`UiBaseView`](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)`<`[`UiEditText`](../-ui-edit-text/index.md)`>, `[`UiEditableActions`](./index.md)`, `[`UiTextViewAssertions`](../../com.kaspersky.components.kautomator.component.text/-ui-text-view-assertions/index.md) |

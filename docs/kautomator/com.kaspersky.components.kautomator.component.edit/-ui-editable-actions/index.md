[kautomator](../../index.md) / [com.kaspersky.components.kautomator.component.edit](../index.md) / [UiEditableActions](./index.md)

# UiEditableActions

`interface UiEditableActions : `[`UiBaseActions`](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/index.md)

Provides editable actions for UiEditText

### Types

| Name | Summary |
|---|---|
| [UiEditableActionType](-ui-editable-action-type/index.md) | `enum class UiEditableActionType : `[`UiOperationType`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation-type/index.md) |

### Inherited Properties

| Name | Summary |
|---|---|
| [view](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/view.md) | `abstract val view: `[`UiObjectInteractionDelegate`](../../com.kaspersky.components.kautomator.intercept.delegate/-ui-object-interaction-delegate/index.md) |

### Functions

| Name | Summary |
|---|---|
| [clearText](clear-text.md) | `open fun clearText(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Clears the text content into the view |
| [replaceText](replace-text.md) | `open fun replaceText(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Replaces the current view text with given |
| [typeText](type-text.md) | `open fun typeText(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Types the given text into the view |

### Inherited Functions

| Name | Summary |
|---|---|
| [click](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/click.md) | `open fun click(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Performs click on view |
| [doubleClick](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/double-click.md) | `open fun doubleClick(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Performs double click on view |
| [longClick](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/long-click.md) | `open fun longClick(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Performs long click on view |

### Inheritors

| Name | Summary |
|---|---|
| [UiEditText](../-ui-edit-text/index.md) | `class UiEditText : `[`UiBaseView`](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)`<`[`UiEditText`](../-ui-edit-text/index.md)`>, `[`UiEditableActions`](./index.md)`, `[`UiTextViewAssertions`](../../com.kaspersky.components.kautomator.component.text/-ui-text-view-assertions/index.md)<br>Ui View with [UiEditableActions](./index.md) and [UiTextViewAssertions](../../com.kaspersky.components.kautomator.component.text/-ui-text-view-assertions/index.md) |

[kautomator](../../index.md) / [com.kaspersky.components.kautomator.component.edit](../index.md) / [UiEditText](./index.md)

# UiEditText

`class UiEditText : `[`UiBaseView`](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)`<`[`UiEditText`](./index.md)`>, `[`UiEditableActions`](../-ui-editable-actions/index.md)`, `[`UiTextViewAssertions`](../../com.kaspersky.components.kautomator.component.text/-ui-text-view-assertions/index.md)

Ui View with [UiEditableActions](../-ui-editable-actions/index.md) and [UiTextViewAssertions](../../com.kaspersky.components.kautomator.component.text/-ui-text-view-assertions/index.md)

**See Also**

[UiEditableActions](../-ui-editable-actions/index.md)

[UiTextViewAssertions](../../com.kaspersky.components.kautomator.component.text/-ui-text-view-assertions/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `UiEditText(selector: `[`UiViewSelector`](../../com.kaspersky.components.kautomator.component.common.builders/-ui-view-selector/index.md)`)`<br>`UiEditText(builder: `[`UiViewBuilder`](../../com.kaspersky.components.kautomator.component.common.builders/-ui-view-builder/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`)` |

### Inherited Properties

| Name | Summary |
|---|---|
| [view](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/view.md) | `val view: `[`UiObjectInteractionDelegate`](../../com.kaspersky.components.kautomator.intercept.delegate/-ui-object-interaction-delegate/index.md) |

### Inherited Functions

| Name | Summary |
|---|---|
| [clearText](../-ui-editable-actions/clear-text.md) | `open fun clearText(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Clears the text content into the view |
| [containsText](../../com.kaspersky.components.kautomator.component.text/-ui-text-view-assertions/contains-text.md) | `open fun containsText(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view contains concrete text |
| [hasAnyText](../../com.kaspersky.components.kautomator.component.text/-ui-text-view-assertions/has-any-text.md) | `open fun hasAnyText(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view has any text |
| [hasEmptyText](../../com.kaspersky.components.kautomator.component.text/-ui-text-view-assertions/has-empty-text.md) | `open fun hasEmptyText(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view has empty text |
| [hasNoText](../../com.kaspersky.components.kautomator.component.text/-ui-text-view-assertions/has-no-text.md) | `open fun hasNoText(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view has not concrete text |
| [hasText](../../com.kaspersky.components.kautomator.component.text/-ui-text-view-assertions/has-text.md) | `open fun hasText(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view has concrete text |
| [invoke](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/invoke.md) | `operator fun invoke(function: `[`T`](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md#T)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Operator that allows usage of DSL style |
| [replaceText](../-ui-editable-actions/replace-text.md) | `open fun replaceText(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Replaces the current view text with given |
| [typeText](../-ui-editable-actions/type-text.md) | `open fun typeText(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Types the given text into the view |

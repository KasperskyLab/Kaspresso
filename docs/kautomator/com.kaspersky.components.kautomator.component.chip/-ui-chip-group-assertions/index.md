[kautomator](../../index.md) / [com.kaspersky.components.kautomator.component.chip](../index.md) / [UiChipGroupAssertions](./index.md)

# UiChipGroupAssertions

`interface UiChipGroupAssertions : `[`UiBaseAssertions`](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/index.md)

Provides assertions for a ChipGroup

### Types

| Name | Summary |
|---|---|
| [UiChipGroupAssertionType](-ui-chip-group-assertion-type/index.md) | `enum class UiChipGroupAssertionType : `[`UiOperationType`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation-type/index.md) |

### Inherited Properties

| Name | Summary |
|---|---|
| [view](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/view.md) | `abstract val view: `[`UiObjectInteractionDelegate`](../../com.kaspersky.components.kautomator.intercept.delegate/-ui-object-interaction-delegate/index.md) |

### Functions

| Name | Summary |
|---|---|
| [hasChipWithText](has-chip-with-text.md) | `open fun hasChipWithText(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`open fun hasChipWithText(pattern: `[`Pattern`](https://developer.android.com/reference/java/util/regex/Pattern.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if chip with given text exists |
| [isChipWithIdSelected](is-chip-with-id-selected.md) | `open fun isChipWithIdSelected(id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if chip with given id is selected |
| [isChipWithIndexSelected](is-chip-with-index-selected.md) | `open fun isChipWithIndexSelected(index: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if chip with given index is selected |
| [isChipWithTextSelected](is-chip-with-text-selected.md) | `open fun isChipWithTextSelected(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if chip with given text is selected`open fun isChipWithTextSelected(pattern: `[`Pattern`](https://developer.android.com/reference/java/util/regex/Pattern.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if chip with given text pattern is selected |
| [isNotChipWithIdSelected](is-not-chip-with-id-selected.md) | `open fun isNotChipWithIdSelected(id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if chip with given id is not selected |
| [isNotChipWithIndexSelected](is-not-chip-with-index-selected.md) | `open fun isNotChipWithIndexSelected(index: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if chip with given index is not selected |
| [isNotChipWithTextSelected](is-not-chip-with-text-selected.md) | `open fun isNotChipWithTextSelected(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if chip with given text is not selected`open fun isNotChipWithTextSelected(pattern: `[`Pattern`](https://developer.android.com/reference/java/util/regex/Pattern.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if chip with given text pattern is not selected |

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
| [UiChipGroup](../-ui-chip-group/index.md) | `class UiChipGroup : `[`UiBaseView`](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)`<`[`UiChipGroup`](../-ui-chip-group/index.md)`>, `[`UiChipGroupActions`](../-ui-chip-group-actions/index.md)`, `[`UiChipGroupAssertions`](./index.md)<br>View for acting and asserting on ChipGroup |

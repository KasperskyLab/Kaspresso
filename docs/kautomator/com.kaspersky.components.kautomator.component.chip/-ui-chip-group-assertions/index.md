[kautomator](../../index.md) / [com.kaspersky.components.kautomator.component.chip](../index.md) / [UiChipGroupAssertions](./index.md)

# UiChipGroupAssertions

`interface UiChipGroupAssertions : `[`UiBaseAssertions`](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/index.md)

Provides assertions for a ChipGroup

### Types

| Name | Summary |
|---|---|
| [UiChipGroupAssertionType](-ui-chip-group-assertion-type/index.md) | `enum class UiChipGroupAssertionType : `[`UiOperationType`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation-type/index.md) |

### Functions

| Name | Summary |
|---|---|
| [hasChipWithText](has-chip-with-text.md) | Checks if chip with given text exists`open fun hasChipWithText(text: String): Unit`<br>`open fun hasChipWithText(pattern: Pattern): Unit` |
| [isChipWithIdSelected](is-chip-with-id-selected.md) | Checks if chip with given id is selected`open fun isChipWithIdSelected(id: String): Unit` |
| [isChipWithIndexSelected](is-chip-with-index-selected.md) | Checks if chip with given index is selected`open fun isChipWithIndexSelected(index: Int): Unit` |
| [isChipWithTextSelected](is-chip-with-text-selected.md) | Checks if chip with given text is selected`open fun isChipWithTextSelected(text: String): Unit`<br>Checks if chip with given text pattern is selected`open fun isChipWithTextSelected(pattern: Pattern): Unit` |
| [isNotChipWithIdSelected](is-not-chip-with-id-selected.md) | Checks if chip with given id is not selected`open fun isNotChipWithIdSelected(id: String): Unit` |
| [isNotChipWithIndexSelected](is-not-chip-with-index-selected.md) | Checks if chip with given index is not selected`open fun isNotChipWithIndexSelected(index: Int): Unit` |
| [isNotChipWithTextSelected](is-not-chip-with-text-selected.md) | Checks if chip with given text is not selected`open fun isNotChipWithTextSelected(text: String): Unit`<br>Checks if chip with given text pattern is not selected`open fun isNotChipWithTextSelected(pattern: Pattern): Unit` |

### Inheritors

| Name | Summary |
|---|---|
| [UiChipGroup](../-ui-chip-group/index.md) | View for acting and asserting on ChipGroup`class UiChipGroup : `[`UiBaseView`](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)`<`[`UiChipGroup`](../-ui-chip-group/index.md)`>, `[`UiChipGroupActions`](../-ui-chip-group-actions/index.md)`, `[`UiChipGroupAssertions`](./index.md) |

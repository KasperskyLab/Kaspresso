[kautomator](../../index.md) / [com.kaspersky.components.kautomator.component.chip](../index.md) / [UiChipGroupActions](./index.md)

# UiChipGroupActions

`interface UiChipGroupActions : `[`UiBaseActions`](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/index.md)

Provides actions for a ChipGroup

### Types

| Name | Summary |
|---|---|
| [UiChipGroupActionType](-ui-chip-group-action-type/index.md) | `enum class UiChipGroupActionType : `[`UiOperationType`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation-type/index.md) |

### Functions

| Name | Summary |
|---|---|
| [selectChipWithId](select-chip-with-id.md) | Selects a chip in ChipGroup with given id`open fun selectChipWithId(id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [selectChipWithIndex](select-chip-with-index.md) | Selects a chip with given index`open fun selectChipWithIndex(index: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [selectChipWithText](select-chip-with-text.md) | Selects a chip with given text`open fun selectChipWithText(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Selects a chip with give text pattern`open fun selectChipWithText(pattern: `[`Pattern`](https://docs.oracle.com/javase/6/docs/api/java/util/regex/Pattern.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [UiChipGroup](../-ui-chip-group/index.md) | View for acting and asserting on ChipGroup`class UiChipGroup : `[`UiBaseView`](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)`<`[`UiChipGroup`](../-ui-chip-group/index.md)`>, `[`UiChipGroupActions`](./index.md)`, `[`UiChipGroupAssertions`](../-ui-chip-group-assertions/index.md) |

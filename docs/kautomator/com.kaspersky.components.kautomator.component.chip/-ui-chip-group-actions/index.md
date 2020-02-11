[kautomator](../../index.md) / [com.kaspersky.components.kautomator.component.chip](../index.md) / [UiChipGroupActions](./index.md)

# UiChipGroupActions

`interface UiChipGroupActions : `[`UiBaseActions`](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/index.md)

Provides actions for a ChipGroup

### Types

| Name | Summary |
|---|---|
| [UiChipGroupActionType](-ui-chip-group-action-type/index.md) | `enum class UiChipGroupActionType : `[`UiOperationType`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation-type/index.md) |

### Inherited Properties

| Name | Summary |
|---|---|
| [view](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/view.md) | `abstract val view: `[`UiObjectInteractionDelegate`](../../com.kaspersky.components.kautomator.intercept.delegate/-ui-object-interaction-delegate/index.md) |

### Functions

| Name | Summary |
|---|---|
| [selectChipWithId](select-chip-with-id.md) | `open fun selectChipWithId(id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Selects a chip in ChipGroup with given id |
| [selectChipWithIndex](select-chip-with-index.md) | `open fun selectChipWithIndex(index: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Selects a chip with given index |
| [selectChipWithText](select-chip-with-text.md) | `open fun selectChipWithText(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Selects a chip with given text`open fun selectChipWithText(pattern: `[`Pattern`](https://developer.android.com/reference/java/util/regex/Pattern.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Selects a chip with give text pattern |

### Inherited Functions

| Name | Summary |
|---|---|
| [click](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/click.md) | `open fun click(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Performs click on view |
| [doubleClick](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/double-click.md) | `open fun doubleClick(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Performs double click on view |
| [longClick](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/long-click.md) | `open fun longClick(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Performs long click on view |

### Inheritors

| Name | Summary |
|---|---|
| [UiChipGroup](../-ui-chip-group/index.md) | `class UiChipGroup : `[`UiBaseView`](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)`<`[`UiChipGroup`](../-ui-chip-group/index.md)`>, `[`UiChipGroupActions`](./index.md)`, `[`UiChipGroupAssertions`](../-ui-chip-group-assertions/index.md)<br>View for acting and asserting on ChipGroup |

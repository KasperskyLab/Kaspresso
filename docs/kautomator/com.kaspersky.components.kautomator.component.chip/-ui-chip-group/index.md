[kautomator](../../index.md) / [com.kaspersky.components.kautomator.component.chip](../index.md) / [UiChipGroup](./index.md)

# UiChipGroup

`class UiChipGroup : `[`UiBaseView`](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)`<`[`UiChipGroup`](./index.md)`>, `[`UiChipGroupActions`](../-ui-chip-group-actions/index.md)`, `[`UiChipGroupAssertions`](../-ui-chip-group-assertions/index.md)

View for acting and asserting on ChipGroup

**See Also**

[UiChipGroupActions](../-ui-chip-group-actions/index.md)

[UiChipGroupAssertions](../-ui-chip-group-assertions/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `UiChipGroup(selector: `[`UiViewSelector`](../../com.kaspersky.components.kautomator.component.common.builders/-ui-view-selector/index.md)`)`<br>`UiChipGroup(builder: `[`UiViewBuilder`](../../com.kaspersky.components.kautomator.component.common.builders/-ui-view-builder/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`)` |

### Inherited Properties

| Name | Summary |
|---|---|
| [view](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/view.md) | `val view: `[`UiObjectInteractionDelegate`](../../com.kaspersky.components.kautomator.intercept.delegate/-ui-object-interaction-delegate/index.md) |

### Inherited Functions

| Name | Summary |
|---|---|
| [hasChipWithText](../-ui-chip-group-assertions/has-chip-with-text.md) | `open fun hasChipWithText(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`open fun hasChipWithText(pattern: `[`Pattern`](https://developer.android.com/reference/java/util/regex/Pattern.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if chip with given text exists |
| [invoke](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/invoke.md) | `operator fun invoke(function: `[`T`](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md#T)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Operator that allows usage of DSL style |
| [isChipWithIdSelected](../-ui-chip-group-assertions/is-chip-with-id-selected.md) | `open fun isChipWithIdSelected(id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if chip with given id is selected |
| [isChipWithIndexSelected](../-ui-chip-group-assertions/is-chip-with-index-selected.md) | `open fun isChipWithIndexSelected(index: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if chip with given index is selected |
| [isChipWithTextSelected](../-ui-chip-group-assertions/is-chip-with-text-selected.md) | `open fun isChipWithTextSelected(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if chip with given text is selected`open fun isChipWithTextSelected(pattern: `[`Pattern`](https://developer.android.com/reference/java/util/regex/Pattern.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if chip with given text pattern is selected |
| [isNotChipWithIdSelected](../-ui-chip-group-assertions/is-not-chip-with-id-selected.md) | `open fun isNotChipWithIdSelected(id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if chip with given id is not selected |
| [isNotChipWithIndexSelected](../-ui-chip-group-assertions/is-not-chip-with-index-selected.md) | `open fun isNotChipWithIndexSelected(index: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if chip with given index is not selected |
| [isNotChipWithTextSelected](../-ui-chip-group-assertions/is-not-chip-with-text-selected.md) | `open fun isNotChipWithTextSelected(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if chip with given text is not selected`open fun isNotChipWithTextSelected(pattern: `[`Pattern`](https://developer.android.com/reference/java/util/regex/Pattern.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if chip with given text pattern is not selected |
| [selectChipWithId](../-ui-chip-group-actions/select-chip-with-id.md) | `open fun selectChipWithId(id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Selects a chip in ChipGroup with given id |
| [selectChipWithIndex](../-ui-chip-group-actions/select-chip-with-index.md) | `open fun selectChipWithIndex(index: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Selects a chip with given index |
| [selectChipWithText](../-ui-chip-group-actions/select-chip-with-text.md) | `open fun selectChipWithText(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Selects a chip with given text`open fun selectChipWithText(pattern: `[`Pattern`](https://developer.android.com/reference/java/util/regex/Pattern.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Selects a chip with give text pattern |

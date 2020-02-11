[kautomator](../../index.md) / [com.kaspersky.components.kautomator.component.check](../index.md) / [UiCheckBox](./index.md)

# UiCheckBox

`class UiCheckBox : `[`UiBaseView`](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)`<`[`UiCheckBox`](./index.md)`>, `[`UiCheckableActions`](../-ui-checkable-actions/index.md)`, `[`UiCheckableAssertions`](../-ui-checkable-assertions/index.md)

Ui View with [UiCheckableActions](../-ui-checkable-actions/index.md) and [UiCheckableAssertions](../-ui-checkable-assertions/index.md)

**See Also**

[UiCheckableActions](../-ui-checkable-actions/index.md)

[UiCheckableAssertions](../-ui-checkable-assertions/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `UiCheckBox(selector: `[`UiViewSelector`](../../com.kaspersky.components.kautomator.component.common.builders/-ui-view-selector/index.md)`)`<br>`UiCheckBox(builder: `[`UiViewBuilder`](../../com.kaspersky.components.kautomator.component.common.builders/-ui-view-builder/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`)` |

### Inherited Properties

| Name | Summary |
|---|---|
| [view](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/view.md) | `val view: `[`UiObjectInteractionDelegate`](../../com.kaspersky.components.kautomator.intercept.delegate/-ui-object-interaction-delegate/index.md) |

### Inherited Functions

| Name | Summary |
|---|---|
| [invoke](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/invoke.md) | `operator fun invoke(function: `[`T`](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md#T)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Operator that allows usage of DSL style |
| [isChecked](../-ui-checkable-assertions/is-checked.md) | `open fun isChecked(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view is checked |
| [isNotChecked](../-ui-checkable-assertions/is-not-checked.md) | `open fun isNotChecked(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view is not checked |
| [setChecked](../-ui-checkable-actions/set-checked.md) | `open fun setChecked(checked: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets checked state of the view |

[kautomator](../../index.md) / [com.kaspersky.components.kautomator.component.common.views](../index.md) / [UiView](./index.md)

# UiView

`class UiView : `[`UiBaseView`](../-ui-base-view/index.md)`<`[`UiView`](./index.md)`>`

Simple view with [UiBaseAction](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/index.md) and
[UiBaseAssertion](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/index.md)

**See Also**

[com.kaspersky.components.kautomator.component.common.actions.UiBaseActions](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/index.md)

[com.kaspersky.components.kautomator.component.common.assertions.UiBaseAssertions](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `UiView(selector: `[`UiViewSelector`](../../com.kaspersky.components.kautomator.component.common.builders/-ui-view-selector/index.md)`)`<br>`UiView(func: `[`UiViewBuilder`](../../com.kaspersky.components.kautomator.component.common.builders/-ui-view-builder/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`)` |

### Inherited Properties

| Name | Summary |
|---|---|
| [view](../-ui-base-view/view.md) | `val view: `[`UiObjectInteractionDelegate`](../../com.kaspersky.components.kautomator.intercept.delegate/-ui-object-interaction-delegate/index.md) |

### Inherited Functions

| Name | Summary |
|---|---|
| [invoke](../-ui-base-view/invoke.md) | `operator fun invoke(function: `[`T`](../-ui-base-view/index.md#T)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Operator that allows usage of DSL style |

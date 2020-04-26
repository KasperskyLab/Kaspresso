[kautomator](../../index.md) / [com.kaspersky.components.kautomator.component.common.views](../index.md) / [UiBaseView](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`UiBaseView(function: `[`UiViewBuilder`](../../com.kaspersky.components.kautomator.component.common.builders/-ui-view-builder/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`)`

Constructs view class with UiObject interaction from given UiViewBuilder

### Parameters

`function` - UiViewBuilder which will result in [UiObjectInteraction](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-object-interaction/index.md)

**See Also**

[UiViewBuilder](../../com.kaspersky.components.kautomator.component.common.builders/-ui-view-builder/index.md)

`UiBaseView(selector: `[`UiViewSelector`](../../com.kaspersky.components.kautomator.component.common.builders/-ui-view-selector/index.md)`)`

Base class for all UiAutomator DSL views

This base class allows create new custom view with ease. All you
have to do is to extend this class, implement all necessarily additional
actions/assertions interfaces and override necessary constructors

### Parameters

`T` - Type of your custom view. Needs to be defined to enable invoke() for descendants
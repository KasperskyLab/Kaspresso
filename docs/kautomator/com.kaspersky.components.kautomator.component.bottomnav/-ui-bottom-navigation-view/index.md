[kautomator](../../index.md) / [com.kaspersky.components.kautomator.component.bottomnav](../index.md) / [UiBottomNavigationView](./index.md)

# UiBottomNavigationView

`class UiBottomNavigationView : `[`UiBaseView`](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)`<`[`UiBottomNavigationView`](./index.md)`>, `[`UiBottomNavigationViewActions`](../-ui-bottom-navigation-view-actions/index.md)`, `[`UiBottomNavigationViewAssertions`](../-ui-bottom-navigation-view-assertions/index.md)

View for acting and asserting on BottomNavigationView

**See Also**

[UiBottomNavigationViewActions](../-ui-bottom-navigation-view-actions/index.md)

[UiBottomNavigationViewAssertions](../-ui-bottom-navigation-view-assertions/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `UiBottomNavigationView(selector: `[`UiViewSelector`](../../com.kaspersky.components.kautomator.component.common.builders/-ui-view-selector/index.md)`)`<br>`UiBottomNavigationView(builder: `[`UiViewBuilder`](../../com.kaspersky.components.kautomator.component.common.builders/-ui-view-builder/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`)` |

### Inherited Properties

| Name | Summary |
|---|---|
| [view](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/view.md) | `val view: `[`UiObjectInteractionDelegate`](../../com.kaspersky.components.kautomator.intercept.delegate/-ui-object-interaction-delegate/index.md) |

### Inherited Functions

| Name | Summary |
|---|---|
| [hasNotSelectedItemWithId](../-ui-bottom-navigation-view-assertions/has-not-selected-item-with-id.md) | `open fun hasNotSelectedItemWithId(id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view's selected menu item id does not match given one. |
| [hasNotSelectedItemWithIndex](../-ui-bottom-navigation-view-assertions/has-not-selected-item-with-index.md) | `open fun hasNotSelectedItemWithIndex(index: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view's selected menu item index does not match given one. Note that this method uses view hierarchy which could be changed at any time. |
| [hasNotSelectedItemWithTitle](../-ui-bottom-navigation-view-assertions/has-not-selected-item-with-title.md) | `open fun hasNotSelectedItemWithTitle(title: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view's selected menu item title does not match given one. Note that this method uses view hierarchy which could be changed at any time. |
| [hasSelectedItemWithId](../-ui-bottom-navigation-view-assertions/has-selected-item-with-id.md) | `open fun hasSelectedItemWithId(id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view's selected menu item id matches given one |
| [hasSelectedItemWithIndex](../-ui-bottom-navigation-view-assertions/has-selected-item-with-index.md) | `open fun hasSelectedItemWithIndex(index: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view's selected menu item index matches given one. Note that this method uses view hierarchy which could be changed at any time. |
| [hasSelectedItemWithTitle](../-ui-bottom-navigation-view-assertions/has-selected-item-with-title.md) | `open fun hasSelectedItemWithTitle(title: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view's selected menu item title matches given one. Note that this method uses view hierarchy which could be changed at any time. |
| [invoke](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/invoke.md) | `operator fun invoke(function: `[`T`](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md#T)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Operator that allows usage of DSL style |
| [setSelectedItemWithId](../-ui-bottom-navigation-view-actions/set-selected-item-with-id.md) | `open fun setSelectedItemWithId(id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Selects menu item with given id |
| [setSelectedItemWithIndex](../-ui-bottom-navigation-view-actions/set-selected-item-with-index.md) | `open fun setSelectedItemWithIndex(index: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Selects menu item with given index. Note that this method uses view hierarchy which could be changed at any time. |
| [setSelectedItemWithTitle](../-ui-bottom-navigation-view-actions/set-selected-item-with-title.md) | `open fun setSelectedItemWithTitle(title: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Selects menu item with given title. Note that this method uses view hierarchy which could be changed at any time. |

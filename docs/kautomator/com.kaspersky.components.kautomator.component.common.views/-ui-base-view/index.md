[kautomator](../../index.md) / [com.kaspersky.components.kautomator.component.common.views](../index.md) / [UiBaseView](./index.md)

# UiBaseView

`open class UiBaseView<out T> : `[`UiBaseActions`](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/index.md)`, `[`UiBaseAssertions`](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/index.md)`, `[`UiInterceptable`](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptable/index.md)`<`[`UiObjectInteraction`](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-object-interaction/index.md)`, `[`UiObjectAssertion`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-object-assertion.md)`, `[`UiObjectAction`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-object-action.md)`>`

Base class for all UiAutomator DSL views

This base class allows create new custom view with ease. All you
have to do is to extend this class, implement all necessarily additional
actions/assertions interfaces and override necessary constructors

### Parameters

`T` - Type of your custom view. Needs to be defined to enable invoke() for descendants

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `UiBaseView(function: `[`UiViewBuilder`](../../com.kaspersky.components.kautomator.component.common.builders/-ui-view-builder/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`)`<br>Constructs view class with UiObject interaction from given UiViewBuilder`UiBaseView(selector: `[`UiViewSelector`](../../com.kaspersky.components.kautomator.component.common.builders/-ui-view-selector/index.md)`)`<br>Base class for all UiAutomator DSL views |

### Properties

| Name | Summary |
|---|---|
| [view](view.md) | `val view: `[`UiObjectInteractionDelegate`](../../com.kaspersky.components.kautomator.intercept.delegate/-ui-object-interaction-delegate/index.md) |

### Functions

| Name | Summary |
|---|---|
| [invoke](invoke.md) | `operator fun invoke(function: `[`T`](index.md#T)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Operator that allows usage of DSL style |

### Inherited Functions

| Name | Summary |
|---|---|
| [click](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/click.md) | `open fun click(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Performs click on view |
| [doubleClick](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/double-click.md) | `open fun doubleClick(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Performs double click on view |
| [intercept](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptable/intercept.md) | `open fun intercept(builder: `[`UiInterceptor.Builder`](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptor/-builder/index.md)`<`[`Interaction`](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptable/index.md#Interaction)`, `[`Assertion`](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptable/index.md#Assertion)`, `[`Action`](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptable/index.md#Action)`>.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets the interceptors for the instance. Interceptors will be invoked on the interaction with the UiView. |
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
| [longClick](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/long-click.md) | `open fun longClick(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Performs long click on view |
| [reset](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptable/reset.md) | `open fun reset(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Removes the interceptors from the instance. |

### Inheritors

| Name | Summary |
|---|---|
| [UiAlertDialog](../../com.kaspersky.components.kautomator.component.dialog/-ui-alert-dialog/index.md) | `class UiAlertDialog : `[`UiBaseView`](./index.md)`<`[`UiAlertDialog`](../../com.kaspersky.components.kautomator.component.dialog/-ui-alert-dialog/index.md)`>`<br>View for acting and asserting on default AlertDialog |
| [UiBottomNavigationView](../../com.kaspersky.components.kautomator.component.bottomnav/-ui-bottom-navigation-view/index.md) | `class UiBottomNavigationView : `[`UiBaseView`](./index.md)`<`[`UiBottomNavigationView`](../../com.kaspersky.components.kautomator.component.bottomnav/-ui-bottom-navigation-view/index.md)`>, `[`UiBottomNavigationViewActions`](../../com.kaspersky.components.kautomator.component.bottomnav/-ui-bottom-navigation-view-actions/index.md)`, `[`UiBottomNavigationViewAssertions`](../../com.kaspersky.components.kautomator.component.bottomnav/-ui-bottom-navigation-view-assertions/index.md)<br>View for acting and asserting on BottomNavigationView |
| [UiButton](../../com.kaspersky.components.kautomator.component.text/-ui-button/index.md) | `class UiButton : `[`UiBaseView`](./index.md)`<`[`UiButton`](../../com.kaspersky.components.kautomator.component.text/-ui-button/index.md)`>, `[`UiTextViewAssertions`](../../com.kaspersky.components.kautomator.component.text/-ui-text-view-assertions/index.md)<br>Ui View with UiBaseActions and UiTextViewAssertions |
| [UiCheckBox](../../com.kaspersky.components.kautomator.component.check/-ui-check-box/index.md) | `class UiCheckBox : `[`UiBaseView`](./index.md)`<`[`UiCheckBox`](../../com.kaspersky.components.kautomator.component.check/-ui-check-box/index.md)`>, `[`UiCheckableActions`](../../com.kaspersky.components.kautomator.component.check/-ui-checkable-actions/index.md)`, `[`UiCheckableAssertions`](../../com.kaspersky.components.kautomator.component.check/-ui-checkable-assertions/index.md)<br>Ui View with [UiCheckableActions](../../com.kaspersky.components.kautomator.component.check/-ui-checkable-actions/index.md) and [UiCheckableAssertions](../../com.kaspersky.components.kautomator.component.check/-ui-checkable-assertions/index.md) |
| [UiChipGroup](../../com.kaspersky.components.kautomator.component.chip/-ui-chip-group/index.md) | `class UiChipGroup : `[`UiBaseView`](./index.md)`<`[`UiChipGroup`](../../com.kaspersky.components.kautomator.component.chip/-ui-chip-group/index.md)`>, `[`UiChipGroupActions`](../../com.kaspersky.components.kautomator.component.chip/-ui-chip-group-actions/index.md)`, `[`UiChipGroupAssertions`](../../com.kaspersky.components.kautomator.component.chip/-ui-chip-group-assertions/index.md)<br>View for acting and asserting on ChipGroup |
| [UiEditText](../../com.kaspersky.components.kautomator.component.edit/-ui-edit-text/index.md) | `class UiEditText : `[`UiBaseView`](./index.md)`<`[`UiEditText`](../../com.kaspersky.components.kautomator.component.edit/-ui-edit-text/index.md)`>, `[`UiEditableActions`](../../com.kaspersky.components.kautomator.component.edit/-ui-editable-actions/index.md)`, `[`UiTextViewAssertions`](../../com.kaspersky.components.kautomator.component.text/-ui-text-view-assertions/index.md)<br>Ui View with [UiEditableActions](../../com.kaspersky.components.kautomator.component.edit/-ui-editable-actions/index.md) and [UiTextViewAssertions](../../com.kaspersky.components.kautomator.component.text/-ui-text-view-assertions/index.md) |
| [UiScrollView](../../com.kaspersky.components.kautomator.component.scroll/-ui-scroll-view/index.md) | `class UiScrollView : `[`UiBaseView`](./index.md)`<`[`UiScrollView`](../../com.kaspersky.components.kautomator.component.scroll/-ui-scroll-view/index.md)`>, `[`UiSwipeableActions`](../../com.kaspersky.components.kautomator.component.common.actions/-ui-swipeable-actions/index.md)`, `[`UiScrollableActions`](../../com.kaspersky.components.kautomator.component.common.actions/-ui-scrollable-actions/index.md) |
| [UiSwitch](../../com.kaspersky.components.kautomator.component.switch/-ui-switch/index.md) | `class UiSwitch : `[`UiBaseView`](./index.md)`<`[`UiSwitch`](../../com.kaspersky.components.kautomator.component.switch/-ui-switch/index.md)`>, `[`UiSwitchableActions`](../../com.kaspersky.components.kautomator.component.switch/-ui-switchable-actions/index.md)`, `[`UiBaseAssertions`](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/index.md)<br>Ui View with [UiSwitchableActions](../../com.kaspersky.components.kautomator.component.switch/-ui-switchable-actions/index.md) and [UiBaseAssertions](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/index.md) |
| [UiTextView](../../com.kaspersky.components.kautomator.component.text/-ui-text-view/index.md) | `class UiTextView : `[`UiBaseView`](./index.md)`<`[`UiTextView`](../../com.kaspersky.components.kautomator.component.text/-ui-text-view/index.md)`>, `[`UiBaseAssertions`](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/index.md)`, `[`UiTextViewAssertions`](../../com.kaspersky.components.kautomator.component.text/-ui-text-view-assertions/index.md)<br>Ui View with UiBaseActions and UiTextViewAssertions |
| [UiView](../-ui-view/index.md) | `class UiView : `[`UiBaseView`](./index.md)`<`[`UiView`](../-ui-view/index.md)`>`<br>Simple view with [UiBaseAction](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/index.md) and [UiBaseAssertion](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/index.md) |

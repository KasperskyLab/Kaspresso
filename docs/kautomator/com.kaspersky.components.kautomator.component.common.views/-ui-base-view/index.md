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
| [&lt;init&gt;](-init-.md) | Constructs view class with UiObject interaction from given UiViewBuilder`UiBaseView(function: `[`UiViewBuilder`](../../com.kaspersky.components.kautomator.component.common.builders/-ui-view-builder/index.md)`.() -> Unit)`<br>Base class for all UiAutomator DSL views`UiBaseView(selector: `[`UiViewSelector`](../../com.kaspersky.components.kautomator.component.common.builders/-ui-view-selector/index.md)`)` |

### Properties

| Name | Summary |
|---|---|
| [view](view.md) | `val view: `[`UiObjectInteractionDelegate`](../../com.kaspersky.components.kautomator.intercept.delegate/-ui-object-interaction-delegate/index.md) |

### Functions

| Name | Summary |
|---|---|
| [invoke](invoke.md) | Operator that allows usage of DSL style`operator fun invoke(function: T.() -> Unit): Unit` |

### Inheritors

| Name | Summary |
|---|---|
| [UiAlertDialog](../../com.kaspersky.components.kautomator.component.dialog/-ui-alert-dialog/index.md) | View for acting and asserting on default AlertDialog`class UiAlertDialog : `[`UiBaseView`](./index.md)`<`[`UiAlertDialog`](../../com.kaspersky.components.kautomator.component.dialog/-ui-alert-dialog/index.md)`>` |
| [UiBottomNavigationView](../../com.kaspersky.components.kautomator.component.bottomnav/-ui-bottom-navigation-view/index.md) | View for acting and asserting on BottomNavigationView`class UiBottomNavigationView : `[`UiBaseView`](./index.md)`<`[`UiBottomNavigationView`](../../com.kaspersky.components.kautomator.component.bottomnav/-ui-bottom-navigation-view/index.md)`>, `[`UiBottomNavigationViewActions`](../../com.kaspersky.components.kautomator.component.bottomnav/-ui-bottom-navigation-view-actions/index.md)`, `[`UiBottomNavigationViewAssertions`](../../com.kaspersky.components.kautomator.component.bottomnav/-ui-bottom-navigation-view-assertions/index.md) |
| [UiButton](../../com.kaspersky.components.kautomator.component.text/-ui-button/index.md) | Ui View with UiBaseActions and UiTextViewAssertions`class UiButton : `[`UiBaseView`](./index.md)`<`[`UiButton`](../../com.kaspersky.components.kautomator.component.text/-ui-button/index.md)`>, `[`UiTextViewAssertions`](../../com.kaspersky.components.kautomator.component.text/-ui-text-view-assertions/index.md) |
| [UiCheckBox](../../com.kaspersky.components.kautomator.component.check/-ui-check-box/index.md) | Ui View with [UiCheckableActions](../../com.kaspersky.components.kautomator.component.check/-ui-checkable-actions/index.md) and [UiCheckableAssertions](../../com.kaspersky.components.kautomator.component.check/-ui-checkable-assertions/index.md)`class UiCheckBox : `[`UiBaseView`](./index.md)`<`[`UiCheckBox`](../../com.kaspersky.components.kautomator.component.check/-ui-check-box/index.md)`>, `[`UiCheckableActions`](../../com.kaspersky.components.kautomator.component.check/-ui-checkable-actions/index.md)`, `[`UiCheckableAssertions`](../../com.kaspersky.components.kautomator.component.check/-ui-checkable-assertions/index.md) |
| [UiChipGroup](../../com.kaspersky.components.kautomator.component.chip/-ui-chip-group/index.md) | View for acting and asserting on ChipGroup`class UiChipGroup : `[`UiBaseView`](./index.md)`<`[`UiChipGroup`](../../com.kaspersky.components.kautomator.component.chip/-ui-chip-group/index.md)`>, `[`UiChipGroupActions`](../../com.kaspersky.components.kautomator.component.chip/-ui-chip-group-actions/index.md)`, `[`UiChipGroupAssertions`](../../com.kaspersky.components.kautomator.component.chip/-ui-chip-group-assertions/index.md) |
| [UiEditText](../../com.kaspersky.components.kautomator.component.edit/-ui-edit-text/index.md) | Ui View with [UiEditableActions](../../com.kaspersky.components.kautomator.component.edit/-ui-editable-actions/index.md) and [UiTextViewAssertions](../../com.kaspersky.components.kautomator.component.text/-ui-text-view-assertions/index.md)`class UiEditText : `[`UiBaseView`](./index.md)`<`[`UiEditText`](../../com.kaspersky.components.kautomator.component.edit/-ui-edit-text/index.md)`>, `[`UiEditableActions`](../../com.kaspersky.components.kautomator.component.edit/-ui-editable-actions/index.md)`, `[`UiTextViewAssertions`](../../com.kaspersky.components.kautomator.component.text/-ui-text-view-assertions/index.md) |
| [UiScrollView](../../com.kaspersky.components.kautomator.component.scroll/-ui-scroll-view/index.md) | `class UiScrollView : `[`UiBaseView`](./index.md)`<`[`UiScrollView`](../../com.kaspersky.components.kautomator.component.scroll/-ui-scroll-view/index.md)`>, `[`UiSwipeableActions`](../../com.kaspersky.components.kautomator.component.common.actions/-ui-swipeable-actions/index.md)`, `[`UiScrollableActions`](../../com.kaspersky.components.kautomator.component.common.actions/-ui-scrollable-actions/index.md) |
| [UiSwitch](../../com.kaspersky.components.kautomator.component.switch/-ui-switch/index.md) | Ui View with [UiSwitchableActions](../../com.kaspersky.components.kautomator.component.switch/-ui-switchable-actions/index.md) and [UiBaseAssertions](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/index.md)`class UiSwitch : `[`UiBaseView`](./index.md)`<`[`UiSwitch`](../../com.kaspersky.components.kautomator.component.switch/-ui-switch/index.md)`>, `[`UiSwitchableActions`](../../com.kaspersky.components.kautomator.component.switch/-ui-switchable-actions/index.md)`, `[`UiBaseAssertions`](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/index.md) |
| [UiTextView](../../com.kaspersky.components.kautomator.component.text/-ui-text-view/index.md) | Ui View with UiBaseActions and UiTextViewAssertions`class UiTextView : `[`UiBaseView`](./index.md)`<`[`UiTextView`](../../com.kaspersky.components.kautomator.component.text/-ui-text-view/index.md)`>, `[`UiBaseAssertions`](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/index.md)`, `[`UiTextViewAssertions`](../../com.kaspersky.components.kautomator.component.text/-ui-text-view-assertions/index.md) |
| [UiView](../-ui-view/index.md) | Simple view with [UiBaseAction](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/index.md) and [UiBaseAssertion](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/index.md)`class UiView : `[`UiBaseView`](./index.md)`<`[`UiView`](../-ui-view/index.md)`>` |

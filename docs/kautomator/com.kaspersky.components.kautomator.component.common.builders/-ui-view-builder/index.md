[kautomator](../../index.md) / [com.kaspersky.components.kautomator.component.common.builders](../index.md) / [UiViewBuilder](./index.md)

# UiViewBuilder

`class UiViewBuilder`

Class for building UiAutomator selectors

This class helps to build selectors for UiAutomator views and get their interactions.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Class for building UiAutomator selectors`UiViewBuilder()` |

### Functions

| Name | Summary |
|---|---|
| [build](build.md) | Returns combined [BySelector](#) with all passed conditions`fun build(): `[`UiViewSelector`](../-ui-view-selector/index.md) |
| [containsText](contains-text.md) | Matches the view which contain given text`fun containsText(text: String): Unit` |
| [isCheckable](is-checkable.md) | Matches the view if it is checkable`fun isCheckable(): Unit` |
| [isChecked](is-checked.md) | Matches the view if it is in CHECKED state`fun isChecked(): Unit` |
| [isClickable](is-clickable.md) | Matches the view if it is clickable`fun isClickable(): Unit` |
| [isDisabled](is-disabled.md) | Matches the view if it is not in ENABLED state`fun isDisabled(): Unit` |
| [isEnabled](is-enabled.md) | Matches the view if it is in ENABLED state`fun isEnabled(): Unit` |
| [isFocusable](is-focusable.md) | Matches the view if it is focusable`fun isFocusable(): Unit` |
| [isInstanceOf](is-instance-of.md) | Matches the view by class instance`fun isInstanceOf(clazz: Class<*>): Unit` |
| [isLongClickable](is-long-clickable.md) | Matches the view if it is long clickable`fun isLongClickable(): Unit` |
| [isNotCheckable](is-not-checkable.md) | Matches the view if it is not checkable`fun isNotCheckable(): Unit` |
| [isNotChecked](is-not-checked.md) | Matches the view if it is not in CHECKED state`fun isNotChecked(): Unit` |
| [isNotClickable](is-not-clickable.md) | Matches the view if it is not clickable`fun isNotClickable(): Unit` |
| [isNotFocusable](is-not-focusable.md) | Matches the view if it is not focusable`fun isNotFocusable(): Unit` |
| [isNotLongClickable](is-not-long-clickable.md) | Matches the view if it is not long clickable`fun isNotLongClickable(): Unit` |
| [isNotScrollable](is-not-scrollable.md) | Matches the view if it is not scrollable`fun isNotScrollable(): Unit` |
| [isNotSelected](is-not-selected.md) | Matches the view if it is not selected`fun isNotSelected(): Unit` |
| [isScrollable](is-scrollable.md) | Matches the view if it is scrollable`fun isScrollable(): Unit` |
| [isSelected](is-selected.md) | Matches the view if it is selected`fun isSelected(): Unit` |
| [textEndsWith](text-ends-with.md) | Matches if the view which text ends with given text`fun textEndsWith(text: String): Unit` |
| [textStartsWith](text-starts-with.md) | Matches if the view which text starts with given text`fun textStartsWith(text: String): Unit` |
| [withAnyText](with-any-text.md) | Matches the view which contains any text`fun withAnyText(): Unit` |
| [withChild](with-child.md) | Matches the view which has child of given matcher`fun withChild(function: `[`UiViewBuilder`](./index.md)`.() -> Unit): Unit` |
| [withClassName](with-class-name.md) | Matches the view which class matches given name`fun withClassName(clazz: String): Unit`<br>`fun withClassName(clazz: Pattern): Unit`<br>`fun withClassName(clazz: Class<*>): Unit` |
| [withContentDescription](with-content-description.md) | Matches the view with given content description`fun withContentDescription(contentDescription: String): Unit`<br>`fun withContentDescription(contentDescription: Pattern): Unit` |
| [withDepth](with-depth.md) | Matches the view that is at a certain depth`fun withDepth(exactDepth: Int): Unit`<br>Matches the view that is in a range of depths`fun withDepth(min: Int, max: Int): Unit` |
| [withDescendant](with-descendant.md) | Matches the view which has descendant of given matcher`fun withDescendant(function: `[`UiViewBuilder`](./index.md)`.() -> Unit): Unit`<br>Matches the view which has descendant of given matcher with the maximum depth under the element to search the descendant`fun withDescendant(maxDepth: Int, function: `[`UiViewBuilder`](./index.md)`.() -> Unit): Unit` |
| [withId](with-id.md) | Matches the view with given package name and resource id`fun withId(packageName: String, resourceId: String): Unit` |
| [withIndex](with-index.md) | Matches only view at given [index](with-index.md#com.kaspersky.components.kautomator.component.common.builders.UiViewBuilder$withIndex(kotlin.Int, kotlin.Function1((com.kaspersky.components.kautomator.component.common.builders.UiViewBuilder, kotlin.Unit)))/index), if there are multiple views that matches`fun withIndex(index: Int, function: `[`UiViewBuilder`](./index.md)`.() -> Unit): Unit` |
| [withMaxDepth](with-max-depth.md) | Matches the view that is no more than a certain depth`fun withMaxDepth(max: Int): Unit` |
| [withMinDepth](with-min-depth.md) | Matches the view that is at least a certain depth`fun withMinDepth(min: Int): Unit` |
| [withoutText](without-text.md) | Matches if the view does not have a given text`fun withoutText(text: String): Unit` |
| [withPackage](with-package.md) | Matches the view with given package name`fun withPackage(packageName: String): Unit`<br>`fun withPackage(packageName: Pattern): Unit` |
| [withResourceName](with-resource-name.md) | Matches the view with given resource name`fun withResourceName(name: String): Unit`<br>`fun withResourceName(pattern: Pattern): Unit`<br>`fun withResourceName(packageName: String, name: String): Unit` |
| [withSelector](with-selector.md) | Matches the view with given custom [BySelector](#)`fun withSelector(selector: BySelector.() -> BySelector): Unit` |
| [withText](with-text.md) | Matches the view with given text`fun withText(text: String): Unit`<br>`fun withText(text: Pattern): Unit` |

//[kautomator](../../index.md)/[com.kaspersky.components.kautomator.component.switch](../index.md)/[UiSwitch](index.md)



# UiSwitch  
 [androidJvm] 

Ui View with [UiSwitchableActions](../-ui-switchable-actions/index.md) and [UiBaseAssertions](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/index.md)

class [UiSwitch](index.md) : [UiBaseView](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)<[UiSwitch](index.md)> , [UiSwitchableActions](../-ui-switchable-actions/index.md), [UiTextViewAssertions](../../com.kaspersky.components.kautomator.component.text/-ui-text-view-assertions/index.md)   


## See also  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| [UiBaseAssertions](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/index.md)| <br><br><br><br>
| [UiSwitchableActions](../-ui-switchable-actions/index.md)| <br><br><br><br>
  


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [UiSwitch](-ui-switch.md)|  [androidJvm] fun [UiSwitch](-ui-switch.md)(selector: [UiViewSelector](../../com.kaspersky.components.kautomator.component.common.builders/-ui-view-selector/index.md))   <br>
| [UiSwitch](-ui-switch.md)|  [androidJvm] fun [UiSwitch](-ui-switch.md)(builder: [UiViewBuilder](../../com.kaspersky.components.kautomator.component.common.builders/-ui-view-builder/index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [click](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/click.md)| [androidJvm]  <br>Content  <br>open override fun [click](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/click.md)()  <br><br><br>
| [containsText](../../com.kaspersky.components.kautomator.component.text/-ui-text-view-assertions/contains-text.md)| [androidJvm]  <br>Brief description  <br><br><br>Checks if the view contains concrete text<br><br>  <br>Content  <br>open override fun [containsText](../../com.kaspersky.components.kautomator.component.text/-ui-text-view-assertions/contains-text.md)(text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>
| [doubleClick](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/double-click.md)| [androidJvm]  <br>Content  <br>open override fun [doubleClick](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/double-click.md)()  <br><br><br>
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hasAnyText](../../com.kaspersky.components.kautomator.component.text/-ui-text-view-assertions/has-any-text.md)| [androidJvm]  <br>Brief description  <br><br><br>Checks if the view has any text<br><br>  <br>Content  <br>open override fun [hasAnyText](../../com.kaspersky.components.kautomator.component.text/-ui-text-view-assertions/has-any-text.md)()  <br><br><br>
| [hasEmptyText](../../com.kaspersky.components.kautomator.component.text/-ui-text-view-assertions/has-empty-text.md)| [androidJvm]  <br>Brief description  <br><br><br>Checks if the view has empty text<br><br>  <br>Content  <br>open override fun [hasEmptyText](../../com.kaspersky.components.kautomator.component.text/-ui-text-view-assertions/has-empty-text.md)()  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [hasNoText](../../com.kaspersky.components.kautomator.component.text/-ui-text-view-assertions/has-no-text.md)| [androidJvm]  <br>Brief description  <br><br><br>Checks if the view has not concrete text<br><br>  <br>Content  <br>open override fun [hasNoText](../../com.kaspersky.components.kautomator.component.text/-ui-text-view-assertions/has-no-text.md)(text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>
| [hasText](../../com.kaspersky.components.kautomator.component.text/-ui-text-view-assertions/has-text.md)| [androidJvm]  <br>Brief description  <br><br><br>Checks if the view has concrete text<br><br>  <br>Content  <br>open override fun [hasText](../../com.kaspersky.components.kautomator.component.text/-ui-text-view-assertions/has-text.md)(text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>
| intercept| [androidJvm]  <br>Brief description  <br><br><br>Sets the interceptors for the instance. Interceptors will be invoked on the interaction with the UiView.<br><br>  <br>Content  <br>open override fun intercept(builder: [UiInterceptor.Builder](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptor/-builder/index.md)<[UiObjectInteraction](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-object-interaction/index.md), [UiOperation](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiObject2>, [UiOperation](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiObject2>>.() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  <br><br><br>
| invoke| [androidJvm]  <br>Brief description  <br><br><br>Operator that allows usage of DSL style<br><br>  <br>Content  <br>operator override fun invoke(function: [UiSwitch](index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  <br><br><br>
| [isClickable](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-clickable.md)| [androidJvm]  <br>Content  <br>open override fun [isClickable](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-clickable.md)()  <br><br><br>
| [isDisabled](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-disabled.md)| [androidJvm]  <br>Content  <br>open override fun [isDisabled](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-disabled.md)()  <br><br><br>
| [isDisplayed](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-displayed.md)| [androidJvm]  <br>Content  <br>open override fun [isDisplayed](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-displayed.md)()  <br><br><br>
| [isEnabled](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-enabled.md)| [androidJvm]  <br>Content  <br>open override fun [isEnabled](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-enabled.md)()  <br><br><br>
| [isFocusable](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-focusable.md)| [androidJvm]  <br>Content  <br>open override fun [isFocusable](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-focusable.md)()  <br><br><br>
| [isFocused](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-focused.md)| [androidJvm]  <br>Content  <br>open override fun [isFocused](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-focused.md)()  <br><br><br>
| [isNotClickable](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-not-clickable.md)| [androidJvm]  <br>Content  <br>open override fun [isNotClickable](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-not-clickable.md)()  <br><br><br>
| [isNotDisplayed](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-not-displayed.md)| [androidJvm]  <br>Content  <br>open override fun [isNotDisplayed](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-not-displayed.md)()  <br><br><br>
| [isNotFocusable](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-not-focusable.md)| [androidJvm]  <br>Content  <br>open override fun [isNotFocusable](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-not-focusable.md)()  <br><br><br>
| [isNotFocused](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-not-focused.md)| [androidJvm]  <br>Content  <br>open override fun [isNotFocused](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-not-focused.md)()  <br><br><br>
| [isNotSelected](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-not-selected.md)| [androidJvm]  <br>Content  <br>open override fun [isNotSelected](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-not-selected.md)()  <br><br><br>
| [isSelected](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-selected.md)| [androidJvm]  <br>Content  <br>open override fun [isSelected](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-selected.md)()  <br><br><br>
| [longClick](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/long-click.md)| [androidJvm]  <br>Content  <br>open override fun [longClick](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/long-click.md)()  <br><br><br>
| [reset](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptable/reset.md)| [androidJvm]  <br>Brief description  <br><br><br>Removes the interceptors from the instance.<br><br>  <br>Content  <br>open override fun [reset](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptable/reset.md)()  <br><br><br>
| [setChecked](../-ui-switchable-actions/set-checked.md)| [androidJvm]  <br>Content  <br>open override fun [setChecked](../-ui-switchable-actions/set-checked.md)(isChecked: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))  <br><br><br>
| [swipeSwitchThumb](../-ui-switchable-actions/swipe-switch-thumb.md)| [androidJvm]  <br>Brief description  <br><br><br>Moves the thumb of the switch to the right<br><br>  <br>Content  <br>open override fun [swipeSwitchThumb](../-ui-switchable-actions/swipe-switch-thumb.md)(direction: [UiSwitchableActions.Direction](../-ui-switchable-actions/-direction/index.md))  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [view](index.md#com.kaspersky.components.kautomator.component.switch/UiSwitch/view/#/PointingToDeclaration/)|  [androidJvm] override val [view](index.md#com.kaspersky.components.kautomator.component.switch/UiSwitch/view/#/PointingToDeclaration/): [UiObjectInteractionDelegate](../../com.kaspersky.components.kautomator.intercept.delegate/-ui-object-interaction-delegate/index.md)   <br>


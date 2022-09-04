//[kautomator](../../index.md)/[com.kaspersky.components.kautomator.component.dialog](../index.md)/[UiAlertDialog](index.md)



# UiAlertDialog  
 [androidJvm] 

View for acting and asserting on default AlertDialog

class [UiAlertDialog](index.md)(**packageName**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [UiBaseView](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)<[UiAlertDialog](index.md)>    


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [UiAlertDialog](-ui-alert-dialog.md)|  [androidJvm] fun [UiAlertDialog](-ui-alert-dialog.md)(packageName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))   <br>


## Types  
  
|  Name|  Summary| 
|---|---|
| [Companion](-companion/index.md)| [androidJvm]  <br>Content  <br>object [Companion](-companion/index.md)  <br><br><br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [click](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/click.md)| [androidJvm]  <br>Brief description  <br><br><br>Performs click on view<br><br>  <br>Content  <br>open override fun [click](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/click.md)()  <br><br><br>
| [doubleClick](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/double-click.md)| [androidJvm]  <br>Brief description  <br><br><br>Performs double click on view<br><br>  <br>Content  <br>open override fun [doubleClick](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/double-click.md)()  <br><br><br>
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| intercept| [androidJvm]  <br>Brief description  <br><br><br>Sets the interceptors for the instance. Interceptors will be invoked on the interaction with the UiView.<br><br>  <br>Content  <br>open override fun intercept(builder: [UiInterceptor.Builder](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptor/-builder/index.md)<[UiObjectInteraction](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-object-interaction/index.md), [UiOperation](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiObject2>, [UiOperation](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiObject2>>.() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  <br><br><br>
| invoke| [androidJvm]  <br>Brief description  <br><br><br>Operator that allows usage of DSL style<br><br>  <br>Content  <br>operator override fun invoke(function: [UiAlertDialog](index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  <br><br><br>
| [isClickable](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-clickable.md)| [androidJvm]  <br>Brief description  <br><br><br>Checks if the view is clickable<br><br>  <br>Content  <br>open override fun [isClickable](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-clickable.md)()  <br><br><br>
| [isDisabled](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-disabled.md)| [androidJvm]  <br>Brief description  <br><br><br>Checks if the view is disabled<br><br>  <br>Content  <br>open override fun [isDisabled](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-disabled.md)()  <br><br><br>
| [isDisplayed](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-displayed.md)| [androidJvm]  <br>Brief description  <br><br><br>Checks if the view is completely displayed<br><br>  <br>Content  <br>open override fun [isDisplayed](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-displayed.md)()  <br><br><br>
| [isEnabled](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-enabled.md)| [androidJvm]  <br>Brief description  <br><br><br>Checks if the view is enabled<br><br>  <br>Content  <br>open override fun [isEnabled](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-enabled.md)()  <br><br><br>
| [isFocusable](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-focusable.md)| [androidJvm]  <br>Brief description  <br><br><br>Checks if the view is focusable<br><br>  <br>Content  <br>open override fun [isFocusable](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-focusable.md)()  <br><br><br>
| [isFocused](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-focused.md)| [androidJvm]  <br>Brief description  <br><br><br>Checks if the view is focused<br><br>  <br>Content  <br>open override fun [isFocused](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-focused.md)()  <br><br><br>
| [isNotClickable](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-not-clickable.md)| [androidJvm]  <br>Brief description  <br><br><br>Checks if the view is not clickable<br><br>  <br>Content  <br>open override fun [isNotClickable](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-not-clickable.md)()  <br><br><br>
| [isNotDisplayed](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-not-displayed.md)| [androidJvm]  <br>Brief description  <br><br><br>Checks if the view is not completely displayed<br><br>  <br>Content  <br>open override fun [isNotDisplayed](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-not-displayed.md)()  <br><br><br>
| [isNotFocusable](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-not-focusable.md)| [androidJvm]  <br>Brief description  <br><br><br>Checks if the view is not focusable<br><br>  <br>Content  <br>open override fun [isNotFocusable](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-not-focusable.md)()  <br><br><br>
| [isNotFocused](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-not-focused.md)| [androidJvm]  <br>Brief description  <br><br><br>Checks if the view is not focused<br><br>  <br>Content  <br>open override fun [isNotFocused](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-not-focused.md)()  <br><br><br>
| [isNotSelected](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-not-selected.md)| [androidJvm]  <br>Brief description  <br><br><br>Checks if the view is not selected<br><br>  <br>Content  <br>open override fun [isNotSelected](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-not-selected.md)()  <br><br><br>
| [isSelected](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-selected.md)| [androidJvm]  <br>Brief description  <br><br><br>Checks if the view is selected<br><br>  <br>Content  <br>open override fun [isSelected](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-selected.md)()  <br><br><br>
| [longClick](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/long-click.md)| [androidJvm]  <br>Brief description  <br><br><br>Performs long click on view<br><br>  <br>Content  <br>open override fun [longClick](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/long-click.md)()  <br><br><br>
| [reset](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptable/reset.md)| [androidJvm]  <br>Brief description  <br><br><br>Removes the interceptors from the instance.<br><br>  <br>Content  <br>open override fun [reset](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptable/reset.md)()  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [message](index.md#com.kaspersky.components.kautomator.component.dialog/UiAlertDialog/message/#/PointingToDeclaration/)|  [androidJvm] val [message](index.md#com.kaspersky.components.kautomator.component.dialog/UiAlertDialog/message/#/PointingToDeclaration/): [UiTextView](../../com.kaspersky.components.kautomator.component.text/-ui-text-view/index.md)   <br>
| [negativeButton](index.md#com.kaspersky.components.kautomator.component.dialog/UiAlertDialog/negativeButton/#/PointingToDeclaration/)|  [androidJvm] val [negativeButton](index.md#com.kaspersky.components.kautomator.component.dialog/UiAlertDialog/negativeButton/#/PointingToDeclaration/): [UiButton](../../com.kaspersky.components.kautomator.component.text/-ui-button/index.md)   <br>
| [neutralButton](index.md#com.kaspersky.components.kautomator.component.dialog/UiAlertDialog/neutralButton/#/PointingToDeclaration/)|  [androidJvm] val [neutralButton](index.md#com.kaspersky.components.kautomator.component.dialog/UiAlertDialog/neutralButton/#/PointingToDeclaration/): [UiButton](../../com.kaspersky.components.kautomator.component.text/-ui-button/index.md)   <br>
| [positiveButton](index.md#com.kaspersky.components.kautomator.component.dialog/UiAlertDialog/positiveButton/#/PointingToDeclaration/)|  [androidJvm] val [positiveButton](index.md#com.kaspersky.components.kautomator.component.dialog/UiAlertDialog/positiveButton/#/PointingToDeclaration/): [UiButton](../../com.kaspersky.components.kautomator.component.text/-ui-button/index.md)   <br>
| [title](index.md#com.kaspersky.components.kautomator.component.dialog/UiAlertDialog/title/#/PointingToDeclaration/)|  [androidJvm] val [title](index.md#com.kaspersky.components.kautomator.component.dialog/UiAlertDialog/title/#/PointingToDeclaration/): [UiTextView](../../com.kaspersky.components.kautomator.component.text/-ui-text-view/index.md)   <br>
| [view](index.md#com.kaspersky.components.kautomator.component.dialog/UiAlertDialog/view/#/PointingToDeclaration/)|  [androidJvm] override val [view](index.md#com.kaspersky.components.kautomator.component.dialog/UiAlertDialog/view/#/PointingToDeclaration/): [UiObjectInteractionDelegate](../../com.kaspersky.components.kautomator.intercept.delegate/-ui-object-interaction-delegate/index.md)   <br>


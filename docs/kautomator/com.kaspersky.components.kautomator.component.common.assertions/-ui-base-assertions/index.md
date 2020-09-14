//[kautomator](../../index.md)/[com.kaspersky.components.kautomator.component.common.assertions](../index.md)/[UiBaseAssertions](index.md)



# UiBaseAssertions  
 [androidJvm] 



Base interface for asserting UiAutomator views



Provides basic assertions that can be performed on any view



interface [UiBaseAssertions](index.md)   


## See also  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| [com.kaspersky.components.kautomator.component.text.UiTextViewAssertions](../../com.kaspersky.components.kautomator.component.text/-ui-text-view-assertions/index.md)| <br><br><br><br>
  


## Types  
  
|  Name|  Summary| 
|---|---|
| [UiBaseAssertionType](-ui-base-assertion-type/index.md)| [androidJvm]  <br>Content  <br>enum [UiBaseAssertionType](-ui-base-assertion-type/index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)<[UiBaseAssertions.UiBaseAssertionType](-ui-base-assertion-type/index.md)> , [UiOperationType](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation-type/index.md)  <br><br><br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [isClickable](is-clickable.md)| [androidJvm]  <br>Brief description  <br><br><br>Checks if the view is clickable<br><br>  <br>Content  <br>open fun [isClickable](is-clickable.md)()  <br><br><br>
| [isDisabled](is-disabled.md)| [androidJvm]  <br>Brief description  <br><br><br>Checks if the view is disabled<br><br>  <br>Content  <br>open fun [isDisabled](is-disabled.md)()  <br><br><br>
| [isDisplayed](is-displayed.md)| [androidJvm]  <br>Brief description  <br><br><br>Checks if the view is completely displayed<br><br>  <br>Content  <br>open fun [isDisplayed](is-displayed.md)()  <br><br><br>
| [isEnabled](is-enabled.md)| [androidJvm]  <br>Brief description  <br><br><br>Checks if the view is enabled<br><br>  <br>Content  <br>open fun [isEnabled](is-enabled.md)()  <br><br><br>
| [isFocusable](is-focusable.md)| [androidJvm]  <br>Brief description  <br><br><br>Checks if the view is focusable<br><br>  <br>Content  <br>open fun [isFocusable](is-focusable.md)()  <br><br><br>
| [isFocused](is-focused.md)| [androidJvm]  <br>Brief description  <br><br><br>Checks if the view is focused<br><br>  <br>Content  <br>open fun [isFocused](is-focused.md)()  <br><br><br>
| [isNotClickable](is-not-clickable.md)| [androidJvm]  <br>Brief description  <br><br><br>Checks if the view is not clickable<br><br>  <br>Content  <br>open fun [isNotClickable](is-not-clickable.md)()  <br><br><br>
| [isNotDisplayed](is-not-displayed.md)| [androidJvm]  <br>Brief description  <br><br><br>Checks if the view is not completely displayed<br><br>  <br>Content  <br>open fun [isNotDisplayed](is-not-displayed.md)()  <br><br><br>
| [isNotFocusable](is-not-focusable.md)| [androidJvm]  <br>Brief description  <br><br><br>Checks if the view is not focusable<br><br>  <br>Content  <br>open fun [isNotFocusable](is-not-focusable.md)()  <br><br><br>
| [isNotFocused](is-not-focused.md)| [androidJvm]  <br>Brief description  <br><br><br>Checks if the view is not focused<br><br>  <br>Content  <br>open fun [isNotFocused](is-not-focused.md)()  <br><br><br>
| [isNotSelected](is-not-selected.md)| [androidJvm]  <br>Brief description  <br><br><br>Checks if the view is not selected<br><br>  <br>Content  <br>open fun [isNotSelected](is-not-selected.md)()  <br><br><br>
| [isSelected](is-selected.md)| [androidJvm]  <br>Brief description  <br><br><br>Checks if the view is selected<br><br>  <br>Content  <br>open fun [isSelected](is-selected.md)()  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [view](index.md#com.kaspersky.components.kautomator.component.common.assertions/UiBaseAssertions/view/#/PointingToDeclaration/)|  [androidJvm] abstract val [view](index.md#com.kaspersky.components.kautomator.component.common.assertions/UiBaseAssertions/view/#/PointingToDeclaration/): [UiObjectInteractionDelegate](../../com.kaspersky.components.kautomator.intercept.delegate/-ui-object-interaction-delegate/index.md)   <br>


## Inheritors  
  
|  Name| 
|---|
| [UiBottomNavigationViewAssertions](../../com.kaspersky.components.kautomator.component.bottomnav/-ui-bottom-navigation-view-assertions/index.md)
| [UiCheckableAssertions](../../com.kaspersky.components.kautomator.component.check/-ui-checkable-assertions/index.md)
| [UiChipGroupAssertions](../../com.kaspersky.components.kautomator.component.chip/-ui-chip-group-assertions/index.md)
| [UiBaseView](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)
| [UiTextView](../../com.kaspersky.components.kautomator.component.text/-ui-text-view/index.md)
| [UiTextViewAssertions](../../com.kaspersky.components.kautomator.component.text/-ui-text-view-assertions/index.md)


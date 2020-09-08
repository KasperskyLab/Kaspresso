//[kautomator](../../index.md)/[com.kaspersky.components.kautomator.component.bottomnav](../index.md)/[UiBottomNavigationViewAssertions](index.md)



# UiBottomNavigationViewAssertions  
 [androidJvm] 

Provides assertions for BottomNavigationview

interface [UiBottomNavigationViewAssertions](index.md) : [UiBaseAssertions](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/index.md)   


## Types  
  
|  Name|  Summary| 
|---|---|
| [UiBottomNavigationViewAssertionType](-ui-bottom-navigation-view-assertion-type/index.md)| [androidJvm]  <br>Content  <br>enum [UiBottomNavigationViewAssertionType](-ui-bottom-navigation-view-assertion-type/index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)<[UiBottomNavigationViewAssertions.UiBottomNavigationViewAssertionType](-ui-bottom-navigation-view-assertion-type/index.md)> , [UiOperationType](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation-type/index.md)  <br><br><br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [hasNotSelectedItemWithId](has-not-selected-item-with-id.md)| [androidJvm]  <br>Brief description  <br><br><br>Checks if the view's selected menu item id does not match given one.<br><br>  <br>Content  <br>open fun [hasNotSelectedItemWithId](has-not-selected-item-with-id.md)(id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>
| [hasNotSelectedItemWithIndex](has-not-selected-item-with-index.md)| [androidJvm]  <br>Brief description  <br><br><br>Checks if the view's selected menu item index does not match given one. Note that this method uses view hierarchy which could be changed at any time.<br><br>  <br>Content  <br>open fun [hasNotSelectedItemWithIndex](has-not-selected-item-with-index.md)(index: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))  <br><br><br>
| [hasNotSelectedItemWithTitle](has-not-selected-item-with-title.md)| [androidJvm]  <br>Brief description  <br><br><br>Checks if the view's selected menu item title does not match given one. Note that this method uses view hierarchy which could be changed at any time.<br><br>  <br>Content  <br>open fun [hasNotSelectedItemWithTitle](has-not-selected-item-with-title.md)(title: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>
| [hasSelectedItemWithId](has-selected-item-with-id.md)| [androidJvm]  <br>Brief description  <br><br><br>Checks if the view's selected menu item id matches given one<br><br>  <br>Content  <br>open fun [hasSelectedItemWithId](has-selected-item-with-id.md)(id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>
| [hasSelectedItemWithIndex](has-selected-item-with-index.md)| [androidJvm]  <br>Brief description  <br><br><br>Checks if the view's selected menu item index matches given one. Note that this method uses view hierarchy which could be changed at any time.<br><br>  <br>Content  <br>open fun [hasSelectedItemWithIndex](has-selected-item-with-index.md)(index: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))  <br><br><br>
| [hasSelectedItemWithTitle](has-selected-item-with-title.md)| [androidJvm]  <br>Brief description  <br><br><br>Checks if the view's selected menu item title matches given one. Note that this method uses view hierarchy which could be changed at any time.<br><br>  <br>Content  <br>open fun [hasSelectedItemWithTitle](has-selected-item-with-title.md)(title: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>
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
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [view](index.md#com.kaspersky.components.kautomator.component.bottomnav/UiBottomNavigationViewAssertions/view/#/PointingToDeclaration/)|  [androidJvm] abstract override val [view](index.md#com.kaspersky.components.kautomator.component.bottomnav/UiBottomNavigationViewAssertions/view/#/PointingToDeclaration/): [UiObjectInteractionDelegate](../../com.kaspersky.components.kautomator.intercept.delegate/-ui-object-interaction-delegate/index.md)   <br>


## Inheritors  
  
|  Name| 
|---|
| [UiBottomNavigationView](../-ui-bottom-navigation-view/index.md)


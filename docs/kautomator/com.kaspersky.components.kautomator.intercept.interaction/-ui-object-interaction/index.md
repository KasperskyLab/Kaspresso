//[kautomator](../../index.md)/[com.kaspersky.components.kautomator.intercept.interaction](../index.md)/[UiObjectInteraction](index.md)



# UiObjectInteraction  
 [androidJvm] 

Provides an interaction to work with the UiView described by [selector](index.md#com.kaspersky.components.kautomator.intercept.interaction/UiObjectInteraction/selector/#/PointingToDeclaration/)

class [UiObjectInteraction](index.md)(**device**: UiDevice, **selector**: [UiViewSelector](../../com.kaspersky.components.kautomator.component.common.builders/-ui-view-selector/index.md), **description**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [UiInteraction](../-ui-interaction/index.md)<[UiOperation](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiObject2>, [UiOperation](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiObject2>>    


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [UiObjectInteraction](-ui-object-interaction.md)|  [androidJvm] fun [UiObjectInteraction](-ui-object-interaction.md)(device: UiDevice, selector: [UiViewSelector](../../com.kaspersky.components.kautomator.component.common.builders/-ui-view-selector/index.md), description: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [check](check.md)| [androidJvm]  <br>Content  <br>open override fun [check](check.md)(assertion: [UiOperation](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiObject2>)  <br><br><br>
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [perform](perform.md)| [androidJvm]  <br>Content  <br>open override fun [perform](perform.md)(action: [UiOperation](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiObject2>)  <br><br><br>
| [toString](to-string.md)| [androidJvm]  <br>Content  <br>open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>
| [tryToFindUiObject](try-to-find-ui-object.md)| [androidJvm]  <br>Brief description  <br><br><br>Tries to find UiObject2 with given selector<br><br>  <br>Content  <br>fun [tryToFindUiObject](try-to-find-ui-object.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [description](index.md#com.kaspersky.components.kautomator.intercept.interaction/UiObjectInteraction/description/#/PointingToDeclaration/)|  [androidJvm] val [description](index.md#com.kaspersky.components.kautomator.intercept.interaction/UiObjectInteraction/description/#/PointingToDeclaration/): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)   <br>
| [device](index.md#com.kaspersky.components.kautomator.intercept.interaction/UiObjectInteraction/device/#/PointingToDeclaration/)|  [androidJvm] val [device](index.md#com.kaspersky.components.kautomator.intercept.interaction/UiObjectInteraction/device/#/PointingToDeclaration/): UiDevice   <br>
| [selector](index.md#com.kaspersky.components.kautomator.intercept.interaction/UiObjectInteraction/selector/#/PointingToDeclaration/)|  [androidJvm] val [selector](index.md#com.kaspersky.components.kautomator.intercept.interaction/UiObjectInteraction/selector/#/PointingToDeclaration/): [UiViewSelector](../../com.kaspersky.components.kautomator.component.common.builders/-ui-view-selector/index.md)   <br>
| [uiObject2](index.md#com.kaspersky.components.kautomator.intercept.interaction/UiObjectInteraction/uiObject2/#/PointingToDeclaration/)|  [androidJvm] var [uiObject2](index.md#com.kaspersky.components.kautomator.intercept.interaction/UiObjectInteraction/uiObject2/#/PointingToDeclaration/): UiObject2?   <br>


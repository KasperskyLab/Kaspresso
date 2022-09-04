//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.uiobjectloader](../index.md)/[UiObjectLoaderProviderImpl](index.md)



# UiObjectLoaderProviderImpl  
 [androidJvm] 

The implementation of the [UiObjectLoaderProvider](../-ui-object-loader-provider/index.md) interface.

class [UiObjectLoaderProviderImpl](index.md)(**logger**: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md)) : [UiObjectLoaderProvider](../-ui-object-loader-provider/index.md)   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [UiObjectLoaderProviderImpl](-ui-object-loader-provider-impl.md)|  [androidJvm] fun [UiObjectLoaderProviderImpl](-ui-object-loader-provider-impl.md)(logger: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md))   <br>


## Types  
  
|  Name|  Summary| 
|---|---|
| [Companion](-companion/index.md)| [androidJvm]  <br>Content  <br>object [Companion](-companion/index.md)  <br><br><br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [handleUiObjectAbsence](handle-ui-object-absence.md)| [androidJvm]  <br>Brief description  <br><br><br>Attempt to find androidx.test.uiautomator.UiObject2 in case of androidx.test.uiautomator.UiObject2 absence or stale<br><br>  <br>Content  <br>open override fun <[T](handle-ui-object-absence.md)> [handleUiObjectAbsence](handle-ui-object-absence.md)(interaction: UiObjectInteraction, action: () -> [T](handle-ui-object-absence.md)): [T](handle-ui-object-absence.md)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


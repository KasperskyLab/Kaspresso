//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.uiobjectloader](../index.md)/[UiObjectLoaderProvider](index.md)



# UiObjectLoaderProvider  
 [androidJvm] 

The interface to provide the functionality to load androidx.test.uiautomator.UiObject2 in case of its absence or stale.

interface [UiObjectLoaderProvider](index.md)   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [handleUiObjectAbsence](handle-ui-object-absence.md)| [androidJvm]  <br>Content  <br>abstract fun <[T](handle-ui-object-absence.md)> [handleUiObjectAbsence](handle-ui-object-absence.md)(interaction: UiObjectInteraction, action: () -> [T](handle-ui-object-absence.md)): [T](handle-ui-object-absence.md)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Inheritors  
  
|  Name| 
|---|
| [UiObjectLoaderBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.loader/-ui-object-loader-behavior-interceptor/index.md)
| [UiObjectLoaderProviderImpl](../-ui-object-loader-provider-impl/index.md)


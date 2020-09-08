//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.behaviorkautomator](../index.md)/[KautomatorBehaviorInterceptor](index.md)



# KautomatorBehaviorInterceptor  
 [androidJvm] 

The interface for all interceptors that change the default interaction in Kautomator. Often it wraps the interaction calls.

interface [KautomatorBehaviorInterceptor](index.md)<[Interaction](index.md), [Assertion](index.md), [Action](index.md)>   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [interceptCheck](intercept-check.md)| [androidJvm]  <br>Brief description  <br><br><br>Called to do some stuff and actually check an interaction with element.<br><br>  <br>Content  <br>abstract fun <[T](intercept-check.md)> [interceptCheck](intercept-check.md)(interaction: [Interaction](index.md), assertion: [Assertion](index.md), activity: () -> [T](intercept-check.md)): [T](intercept-check.md)  <br><br><br>
| [interceptPerform](intercept-perform.md)| [androidJvm]  <br>Brief description  <br><br><br>Called to do some stuff and actually perform an interaction with element.<br><br>  <br>Content  <br>abstract fun <[T](intercept-perform.md)> [interceptPerform](intercept-perform.md)(interaction: [Interaction](index.md), action: [Action](index.md), activity: () -> [T](intercept-perform.md)): [T](intercept-perform.md)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Inheritors  
  
|  Name| 
|---|
| [DeviceBehaviorInterceptor](../-device-behavior-interceptor/index.md)
| [ObjectBehaviorInterceptor](../-object-behavior-interceptor/index.md)


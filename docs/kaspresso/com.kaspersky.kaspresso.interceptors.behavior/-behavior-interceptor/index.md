//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.behavior](../index.md)/[BehaviorInterceptor](index.md)



# BehaviorInterceptor  
 [androidJvm] 

The interface for all interceptors that change the default interaction in Kakao=>Espresso. Often it wraps the interaction calls.

interface [BehaviorInterceptor](index.md)<[Interaction](index.md)>   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [intercept](intercept.md)| [androidJvm]  <br>Brief description  <br><br><br>Called to do some stuff and actually perform an interaction with element.<br><br>  <br>Content  <br>abstract fun <[T](intercept.md)> [intercept](intercept.md)(interaction: [Interaction](index.md), action: () -> [T](intercept.md)): [T](intercept.md)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Inheritors  
  
|  Name| 
|---|
| [DataBehaviorInterceptor](../-data-behavior-interceptor/index.md)
| [ViewBehaviorInterceptor](../-view-behavior-interceptor/index.md)
| [WebBehaviorInterceptor](../-web-behavior-interceptor/index.md)


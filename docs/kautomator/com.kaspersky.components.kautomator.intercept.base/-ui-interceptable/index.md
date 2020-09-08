//[kautomator](../../index.md)/[com.kaspersky.components.kautomator.intercept.base](../index.md)/[UiInterceptable](index.md)



# UiInterceptable  
 [androidJvm] interface [UiInterceptable](index.md)<[Interaction](index.md), [Assertion](index.md), [Action](index.md)>   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [intercept](intercept.md)| [androidJvm]  <br>Brief description  <br><br><br>Sets the interceptors for the instance. Interceptors will be invoked on the interaction with the UiView.<br><br>  <br>Content  <br>open fun [intercept](intercept.md)(builder: [UiInterceptor.Builder](../-ui-interceptor/-builder/index.md)<[Interaction](index.md), [Assertion](index.md), [Action](index.md)>.() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  <br><br><br>
| [reset](reset.md)| [androidJvm]  <br>Brief description  <br><br><br>Removes the interceptors from the instance.<br><br>  <br>Content  <br>open fun [reset](reset.md)()  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [view](index.md#com.kaspersky.components.kautomator.intercept.base/UiInterceptable/view/#/PointingToDeclaration/)|  [androidJvm] abstract val [view](index.md#com.kaspersky.components.kautomator.intercept.base/UiInterceptable/view/#/PointingToDeclaration/): [UiDelegate](../../com.kaspersky.components.kautomator.intercept.delegate/-ui-delegate/index.md)<[Interaction](index.md), [Assertion](index.md), [Action](index.md)>   <br>


## Inheritors  
  
|  Name| 
|---|
| [UiBaseView](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)
| [UiSystem](../../com.kaspersky.components.kautomator.system/-ui-system/index.md)


//[kautomator](../../../index.md)/[com.kaspersky.components.kautomator.intercept.base](../../index.md)/[UiInterceptor](../index.md)/[Builder](index.md)



# Builder  
 [androidJvm] 

Builder class that is used to build a single instance of [UiInterceptor](../index.md).

class [Builder](index.md)<[Interaction](index.md), [Assertion](index.md), [Action](index.md)>   


## See also  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| [UiInterceptor](../index.md)| <br><br><br><br>
  


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [Builder](-builder.md)|  [androidJvm] fun [Builder](-builder.md)()   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [onAll](on-all.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>Sets the interceptor for the check and execute operations for a given interaction. If overridden, breaks the call chain of operation and transfers the responsibility to invoke the UiAutomator on the developer.<br><br><br><br>This interceptor is prioritized and is being invoked first for both operations.<br><br><br><br>  <br>Content  <br>fun [onAll](on-all.md)(isOverride: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), interceptor: ([Interaction](index.md)) -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  <br><br><br>
| [onCheck](on-check.md)| [androidJvm]  <br>Brief description  <br><br><br>Sets the interceptor for the check operation for a given interaction. If overridden, breaks the call chain of operation and transfers the responsibility to invoke the UiAutomator on the developer.<br><br>  <br>Content  <br>fun [onCheck](on-check.md)(isOverride: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), interceptor: ([Interaction](index.md), [Assertion](index.md)) -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  <br><br><br>
| [onPerform](on-perform.md)| [androidJvm]  <br>Brief description  <br><br><br>Sets the interceptor for the execute operation for a given interaction. If overridden, breaks the call chain of operation and transfers the responsibility to invoke the UiAutomator on the developer.<br><br>  <br>Content  <br>fun [onPerform](on-perform.md)(isOverride: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), interceptor: ([Interaction](index.md), [Action](index.md)) -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


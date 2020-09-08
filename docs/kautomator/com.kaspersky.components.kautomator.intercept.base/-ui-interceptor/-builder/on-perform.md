//[kautomator](../../../index.md)/[com.kaspersky.components.kautomator.intercept.base](../../index.md)/[UiInterceptor](../index.md)/[Builder](index.md)/[onPerform](on-perform.md)



# onPerform  
[androidJvm]  
Brief description  


Sets the interceptor for the execute operation for a given interaction. If overridden, breaks the call chain of operation and transfers the responsibility to invoke the UiAutomator on the developer.



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| interceptor| <br><br>lambda with intercepting logic<br><br>
| isOverride| <br><br>if true - breaks the call chain, false otherwise<br><br>
  
  
Content  
fun [onPerform](on-perform.md)(isOverride: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), interceptor: ([Interaction](index.md), [Action](index.md)) -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  




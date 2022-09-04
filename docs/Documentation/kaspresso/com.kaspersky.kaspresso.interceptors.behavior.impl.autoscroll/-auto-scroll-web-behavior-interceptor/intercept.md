//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll](../index.md)/[AutoScrollWebBehaviorInterceptor](index.md)/[intercept](intercept.md)



# intercept  
[androidJvm]  
Brief description  


Wraps the given action invocation with the autoscrolling on failure.



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| action| <br><br>the action to invoke.<br><br>
| interaction| <br><br>the intercepted Web.WebInteraction.<br><br>
  
  
Content  
open override fun <[T](intercept.md)> [intercept](intercept.md)(interaction: Web.WebInteraction<*>, action: () -> [T](intercept.md)): [T](intercept.md)  




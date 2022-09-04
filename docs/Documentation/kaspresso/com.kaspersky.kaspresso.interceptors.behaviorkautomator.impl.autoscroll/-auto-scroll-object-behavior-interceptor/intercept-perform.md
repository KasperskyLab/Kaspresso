//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.autoscroll](../index.md)/[AutoScrollObjectBehaviorInterceptor](index.md)/[interceptPerform](intercept-perform.md)



# interceptPerform  
[androidJvm]  
Brief description  


Wraps the given action invocation with the autoscrolling on failure.



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| action| <br><br>the assertion to invoke.<br><br>
| interaction| <br><br>the intercepted UiObjectInteraction.<br><br>
  
  
Content  
open override fun <[T](intercept-perform.md)> [interceptPerform](intercept-perform.md)(interaction: UiObjectInteraction, action: UiOperation<UiObject2>, activity: () -> [T](intercept-perform.md)): [T](intercept-perform.md)  




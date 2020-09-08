//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.autoscroll](../index.md)/[AutoScrollObjectBehaviorInterceptor](index.md)/[interceptCheck](intercept-check.md)



# interceptCheck  
[androidJvm]  
Brief description  


Wraps the given assertion invocation with the autoscrolling on failure.



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| assertion| <br><br>the assertion to invoke.<br><br>
| interaction| <br><br>the intercepted UiObjectInteraction.<br><br>
  
  
Content  
open override fun <[T](intercept-check.md)> [interceptCheck](intercept-check.md)(interaction: UiObjectInteraction, assertion: UiOperation<UiObject2>, activity: () -> [T](intercept-check.md)): [T](intercept-check.md)  




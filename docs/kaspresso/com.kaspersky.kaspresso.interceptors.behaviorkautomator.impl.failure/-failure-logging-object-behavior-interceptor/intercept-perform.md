//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.failure](../index.md)/[FailureLoggingObjectBehaviorInterceptor](index.md)/[interceptPerform](intercept-perform.md)



# interceptPerform  
[androidJvm]  
Brief description  


Wraps the given activity invocation with the failure logging.



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| action| <br><br>the intercepted UiObjectAction.<br><br>
| activity| <br><br>the activity to invoke.<br><br>
| interaction| <br><br>the intercepted UiObjectInteraction.<br><br>
  
  
Content  
open override fun <[T](intercept-perform.md)> [interceptPerform](intercept-perform.md)(interaction: UiObjectInteraction, action: UiOperation<UiObject2>, activity: () -> [T](intercept-perform.md)): [T](intercept-perform.md)  




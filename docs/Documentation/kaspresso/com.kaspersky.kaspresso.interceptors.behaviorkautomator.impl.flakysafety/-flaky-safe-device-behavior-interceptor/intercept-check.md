//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.flakysafety](../index.md)/[FlakySafeDeviceBehaviorInterceptor](index.md)/[interceptCheck](intercept-check.md)



# interceptCheck  
[androidJvm]  
Brief description  


Wraps the given activity invocation with the flaky safety.



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| activity| <br><br>the activity to invoke.<br><br>
| assertion| <br><br>the intercepted UiDeviceAssertion.<br><br>
| interaction| <br><br>the intercepted UiDeviceInteraction.<br><br>
  
  
Content  
open override fun <[T](intercept-check.md)> [interceptCheck](intercept-check.md)(interaction: UiDeviceInteraction, assertion: UiOperation<UiDevice>, activity: () -> [T](intercept-check.md)): [T](intercept-check.md)  




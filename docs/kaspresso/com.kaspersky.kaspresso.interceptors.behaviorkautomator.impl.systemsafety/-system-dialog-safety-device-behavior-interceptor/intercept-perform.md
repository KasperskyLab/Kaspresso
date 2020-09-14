//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.systemsafety](../index.md)/[SystemDialogSafetyDeviceBehaviorInterceptor](index.md)/[interceptPerform](intercept-perform.md)



# interceptPerform  
[androidJvm]  
Brief description  


Wraps the given activity invocation with the system dialog safety.



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| action| <br><br>the intercepted UiDeviceAction.<br><br>
| activity| <br><br>the activity to invoke.<br><br>
| interaction| <br><br>the intercepted UiDeviceInteraction.<br><br>
  
  
Content  
open override fun <[T](intercept-perform.md)> [interceptPerform](intercept-perform.md)(interaction: UiDeviceInteraction, action: UiOperation<UiDevice>, activity: () -> [T](intercept-perform.md)): [T](intercept-perform.md)  




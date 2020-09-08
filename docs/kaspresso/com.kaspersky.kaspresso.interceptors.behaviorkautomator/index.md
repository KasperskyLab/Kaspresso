//[kaspresso](../index.md)/[com.kaspersky.kaspresso.interceptors.behaviorkautomator](index.md)



# Package com.kaspersky.kaspresso.interceptors.behaviorkautomator  


## Types  
  
|  Name|  Summary| 
|---|---|
| [DeviceBehaviorInterceptor](-device-behavior-interceptor/index.md)| [androidJvm]  <br>Brief description  <br><br><br>The derived from [KautomatorBehaviorInterceptor](-kautomator-behavior-interceptor/index.md) interface for intercepting UiDeviceInteraction.perform and UiDeviceInteraction.check behavior.<br><br>  <br>Content  <br>interface [DeviceBehaviorInterceptor](-device-behavior-interceptor/index.md) : [KautomatorBehaviorInterceptor](-kautomator-behavior-interceptor/index.md)<UiDeviceInteraction, UiOperation<UiDevice>, UiOperation<UiDevice>>   <br><br><br>
| [KautomatorBehaviorInterceptor](-kautomator-behavior-interceptor/index.md)| [androidJvm]  <br>Brief description  <br><br><br>The interface for all interceptors that change the default interaction in Kautomator. Often it wraps the interaction calls.<br><br>  <br>Content  <br>interface [KautomatorBehaviorInterceptor](-kautomator-behavior-interceptor/index.md)<[Interaction](-kautomator-behavior-interceptor/index.md), [Assertion](-kautomator-behavior-interceptor/index.md), [Action](-kautomator-behavior-interceptor/index.md)>  <br><br><br>
| [ObjectBehaviorInterceptor](-object-behavior-interceptor/index.md)| [androidJvm]  <br>Brief description  <br><br><br>The derived from [KautomatorBehaviorInterceptor](-kautomator-behavior-interceptor/index.md) interface for intercepting UiObjectInteraction.perform and UiObjectInteraction.check behavior.<br><br>  <br>Content  <br>interface [ObjectBehaviorInterceptor](-object-behavior-interceptor/index.md) : [KautomatorBehaviorInterceptor](-kautomator-behavior-interceptor/index.md)<UiObjectInteraction, UiOperation<UiObject2>, UiOperation<UiObject2>>   <br><br><br>


//[kaspresso](../index.md)/[com.kaspersky.kaspresso.interceptors.watcher.kautomator](index.md)



# Package com.kaspersky.kaspresso.interceptors.watcher.kautomator  


## Types  
  
|  Name|  Summary| 
|---|---|
| [DeviceWatcherInterceptor](-device-watcher-interceptor/index.md)| [androidJvm]  <br>Brief description  <br><br><br>The derived from [KautomatorWatcherInterceptor](-kautomator-watcher-interceptor/index.md) interface for intercepting (only watching) UiDeviceInteraction.perform and UiDeviceInteraction.check behavior.<br><br>  <br>Content  <br>interface [DeviceWatcherInterceptor](-device-watcher-interceptor/index.md) : [KautomatorWatcherInterceptor](-kautomator-watcher-interceptor/index.md)<UiDeviceInteraction, UiOperation<UiDevice>, UiOperation<UiDevice>>   <br><br><br>
| [KautomatorWatcherInterceptor](-kautomator-watcher-interceptor/index.md)| [androidJvm]  <br>Brief description  <br><br><br>The interface for all interceptors that are watching the default interaction in Kautomator.<br><br>  <br>Content  <br>interface [KautomatorWatcherInterceptor](-kautomator-watcher-interceptor/index.md)<[Interaction](-kautomator-watcher-interceptor/index.md), [Assertion](-kautomator-watcher-interceptor/index.md), [Action](-kautomator-watcher-interceptor/index.md)>  <br><br><br>
| [ObjectWatcherInterceptor](-object-watcher-interceptor/index.md)| [androidJvm]  <br>Brief description  <br><br><br>The derived from [KautomatorWatcherInterceptor](-kautomator-watcher-interceptor/index.md) interface for intercepting (only watching) UiObjectInteraction.perform and UiObjectInteraction.check behavior.<br><br>  <br>Content  <br>interface [ObjectWatcherInterceptor](-object-watcher-interceptor/index.md) : [KautomatorWatcherInterceptor](-kautomator-watcher-interceptor/index.md)<UiObjectInteraction, UiOperation<UiObject2>, UiOperation<UiObject2>>   <br><br><br>


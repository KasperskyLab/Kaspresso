//[kaspresso](../index.md)/[com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.failure](index.md)



# Package com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.failure  


## Types  
  
|  Name|  Summary| 
|---|---|
| [FailureLoggingDeviceBehaviorInterceptor](-failure-logging-device-behavior-interceptor/index.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>The implementation of [DeviceBehaviorInterceptor](../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-device-behavior-interceptor/index.md) and [FailureLoggingProvider](../com.kaspersky.kaspresso.failure/-failure-logging-provider/index.md) interfaces. Provides failure logging functionality for UiDeviceInteraction.perform and UiDeviceInteraction.check calls.<br><br><br><br>By default, this interceptor is not used in Kaspresso. If you desire to change result log (especially in case of an error) we recommend to use [FailureLoggingProvider](../com.kaspersky.kaspresso.failure/-failure-logging-provider/index.md) directly<br><br><br><br>  <br>Content  <br>class [FailureLoggingDeviceBehaviorInterceptor](-failure-logging-device-behavior-interceptor/index.md)(**logger**: [UiTestLogger](../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md)) : [DeviceBehaviorInterceptor](../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-device-behavior-interceptor/index.md), [FailureLoggingProvider](../com.kaspersky.kaspresso.failure/-failure-logging-provider/index.md)  <br><br><br>
| [FailureLoggingObjectBehaviorInterceptor](-failure-logging-object-behavior-interceptor/index.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>The implementation of [ObjectBehaviorInterceptor](../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-object-behavior-interceptor/index.md) and [FailureLoggingProvider](../com.kaspersky.kaspresso.failure/-failure-logging-provider/index.md) interfaces. Provides failure logging functionality for UiObjectInteraction.perform and UiObjectInteraction.check calls.<br><br><br><br>By default, this interceptor is not used in Kaspresso. If you desire to change result log (especially in case of an error) we recommend to use [FailureLoggingProvider](../com.kaspersky.kaspresso.failure/-failure-logging-provider/index.md) directly<br><br><br><br>  <br>Content  <br>class [FailureLoggingObjectBehaviorInterceptor](-failure-logging-object-behavior-interceptor/index.md)(**logger**: [UiTestLogger](../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md)) : [ObjectBehaviorInterceptor](../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-object-behavior-interceptor/index.md), [FailureLoggingProvider](../com.kaspersky.kaspresso.failure/-failure-logging-provider/index.md)  <br><br><br>


[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.failure](../index.md) / [FailureLoggingDeviceBehaviorInterceptor](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`FailureLoggingDeviceBehaviorInterceptor(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`)`

The implementation of [DeviceBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-device-behavior-interceptor.md) and [FailureLoggingProvider](../../com.kaspersky.kaspresso.failure/-failure-logging-provider/index.md) interfaces.
Provides failure logging functionality for [UiDeviceInteraction.perform](#) and [UiDeviceInteraction.check](#) calls.

By default, this interceptor is not used in Kaspresso.
If you desire to change result log (especially in case of an error) we recommend to use [FailureLoggingProvider](../../com.kaspersky.kaspresso.failure/-failure-logging-provider/index.md) directly


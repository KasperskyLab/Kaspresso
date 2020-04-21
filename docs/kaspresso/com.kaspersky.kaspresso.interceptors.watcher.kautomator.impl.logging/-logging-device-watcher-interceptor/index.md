[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.kautomator.impl.logging](../index.md) / [LoggingDeviceWatcherInterceptor](./index.md)

# LoggingDeviceWatcherInterceptor

`class LoggingDeviceWatcherInterceptor : `[`DeviceWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.kautomator/-device-watcher-interceptor.md)

The implementation of [DeviceWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.kautomator/-device-watcher-interceptor.md) that logs info about [UiDeviceAssertion](#) or [UiDeviceAction](#)
and [UiDeviceInteraction](#) on which its activities are performing.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `LoggingDeviceWatcherInterceptor(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`)`<br>The implementation of [DeviceWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.kautomator/-device-watcher-interceptor.md) that logs info about [UiDeviceAssertion](#) or [UiDeviceAction](#) and [UiDeviceInteraction](#) on which its activities are performing. |

### Functions

| Name | Summary |
|---|---|
| [interceptCheck](intercept-check.md) | `fun interceptCheck(interaction: UiDeviceInteraction, assertion: UiDeviceAssertion): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Writes info to [logger](#). |
| [interceptPerform](intercept-perform.md) | `fun interceptPerform(interaction: UiDeviceInteraction, action: UiDeviceAction): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Writes info to [logger](#). |

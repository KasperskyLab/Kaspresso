[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.kautomator.impl.logging](../index.md) / [LoggingDeviceWatcherInterceptor](./index.md)

# LoggingDeviceWatcherInterceptor

`class LoggingDeviceWatcherInterceptor : `[`DeviceWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.kautomator/-device-watcher-interceptor.md)

The implementation of [DeviceWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.kautomator/-device-watcher-interceptor.md) that logs info about [UiDeviceAssertion](#) or [UiDeviceAction](#)
and [UiDeviceInteraction](#) on which its activities are performing.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The implementation of [DeviceWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.kautomator/-device-watcher-interceptor.md) that logs info about [UiDeviceAssertion](#) or [UiDeviceAction](#) and [UiDeviceInteraction](#) on which its activities are performing.`LoggingDeviceWatcherInterceptor(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [interceptCheck](intercept-check.md) | Writes info to [logger](#).`fun interceptCheck(interaction: UiDeviceInteraction, assertion: UiDeviceAssertion): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [interceptPerform](intercept-perform.md) | Writes info to [logger](#).`fun interceptPerform(interaction: UiDeviceInteraction, action: UiDeviceAction): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.kautomator.impl.logging](../index.md) / [LoggingObjectWatcherInterceptor](./index.md)

# LoggingObjectWatcherInterceptor

`class LoggingObjectWatcherInterceptor : `[`ObjectWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.kautomator/-object-watcher-interceptor.md)

The implementation of [ObjectWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.kautomator/-object-watcher-interceptor.md) that logs info about [UiObjectAssertion](#) or [UiObjectAction](#)
and [UiObjectInteraction](#) on which its activities are performing.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The implementation of [ObjectWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.kautomator/-object-watcher-interceptor.md) that logs info about [UiObjectAssertion](#) or [UiObjectAction](#) and [UiObjectInteraction](#) on which its activities are performing.`LoggingObjectWatcherInterceptor(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [interceptCheck](intercept-check.md) | Writes info to [logger](#).`fun interceptCheck(interaction: UiObjectInteraction, assertion: UiObjectAssertion): Unit` |
| [interceptPerform](intercept-perform.md) | Writes info to [logger](#).`fun interceptPerform(interaction: UiObjectInteraction, action: UiObjectAction): Unit` |

[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.kautomator.impl.logging](../index.md) / [LoggingObjectWatcherInterceptor](./index.md)

# LoggingObjectWatcherInterceptor

`class LoggingObjectWatcherInterceptor : `[`ObjectWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.kautomator/-object-watcher-interceptor.md)

The implementation of [ObjectWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.kautomator/-object-watcher-interceptor.md) that logs info about [UiObjectAssertion](#) or [UiObjectAction](#)
and [UiObjectInteraction](#) on which its activities are performing.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `LoggingObjectWatcherInterceptor(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`)`<br>The implementation of [ObjectWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.kautomator/-object-watcher-interceptor.md) that logs info about [UiObjectAssertion](#) or [UiObjectAction](#) and [UiObjectInteraction](#) on which its activities are performing. |

### Functions

| Name | Summary |
|---|---|
| [interceptCheck](intercept-check.md) | `fun interceptCheck(interaction: UiObjectInteraction, assertion: UiObjectAssertion): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Writes info to [logger](#). |
| [interceptPerform](intercept-perform.md) | `fun interceptPerform(interaction: UiObjectInteraction, action: UiObjectAction): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Writes info to [logger](#). |

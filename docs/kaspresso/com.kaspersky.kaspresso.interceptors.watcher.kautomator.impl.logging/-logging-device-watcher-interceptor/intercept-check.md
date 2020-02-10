[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.kautomator.impl.logging](../index.md) / [LoggingDeviceWatcherInterceptor](index.md) / [interceptCheck](./intercept-check.md)

# interceptCheck

`fun interceptCheck(interaction: UiDeviceInteraction, assertion: UiDeviceAssertion): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Overrides [KautomatorWatcherInterceptor.interceptCheck](../../com.kaspersky.kaspresso.interceptors.watcher.kautomator/-kautomator-watcher-interceptor/intercept-check.md)

Writes info to [logger](#).

### Parameters

`interaction` - a Kautomator UiInteraction on which [assertion](intercept-check.md#com.kaspersky.kaspresso.interceptors.watcher.kautomator.impl.logging.LoggingDeviceWatcherInterceptor$interceptCheck(com.kaspersky.components.kautomator.intercept.interaction.UiDeviceInteraction, com.kaspersky.components.kautomator.intercept.operation.UiOperation((androidx.test.uiautomator.UiDevice)))/assertion) is performed

`assertion` - responsible for performing an activity (assertion) on the given [interaction](intercept-check.md#com.kaspersky.kaspresso.interceptors.watcher.kautomator.impl.logging.LoggingDeviceWatcherInterceptor$interceptCheck(com.kaspersky.components.kautomator.intercept.interaction.UiDeviceInteraction, com.kaspersky.components.kautomator.intercept.operation.UiOperation((androidx.test.uiautomator.UiDevice)))/interaction)
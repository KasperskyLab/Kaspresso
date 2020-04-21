[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.kautomator.impl.logging](../index.md) / [LoggingDeviceWatcherInterceptor](index.md) / [interceptPerform](./intercept-perform.md)

# interceptPerform

`fun interceptPerform(interaction: UiDeviceInteraction, action: UiDeviceAction): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Overrides [KautomatorWatcherInterceptor.interceptPerform](../../com.kaspersky.kaspresso.interceptors.watcher.kautomator/-kautomator-watcher-interceptor/intercept-perform.md)

Writes info to [logger](#).

### Parameters

`interaction` - a Kautomator UiInteraction on which [action](intercept-perform.md#com.kaspersky.kaspresso.interceptors.watcher.kautomator.impl.logging.LoggingDeviceWatcherInterceptor$interceptPerform(com.kaspersky.components.kautomator.intercept.interaction.UiDeviceInteraction, com.kaspersky.components.kautomator.intercept.operation.UiOperation((androidx.test.uiautomator.UiDevice)))/action) is performed

`action` - responsible for performing an activity (action) on the given [interaction](intercept-perform.md#com.kaspersky.kaspresso.interceptors.watcher.kautomator.impl.logging.LoggingDeviceWatcherInterceptor$interceptPerform(com.kaspersky.components.kautomator.intercept.interaction.UiDeviceInteraction, com.kaspersky.components.kautomator.intercept.operation.UiOperation((androidx.test.uiautomator.UiDevice)))/interaction)
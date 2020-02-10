[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.kautomator.impl.logging](../index.md) / [LoggingObjectWatcherInterceptor](index.md) / [interceptPerform](./intercept-perform.md)

# interceptPerform

`fun interceptPerform(interaction: UiObjectInteraction, action: UiObjectAction): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Overrides [KautomatorWatcherInterceptor.interceptPerform](../../com.kaspersky.kaspresso.interceptors.watcher.kautomator/-kautomator-watcher-interceptor/intercept-perform.md)

Writes info to [logger](#).

### Parameters

`interaction` - a Kautomator UiInteraction on which [action](intercept-perform.md#com.kaspersky.kaspresso.interceptors.watcher.kautomator.impl.logging.LoggingObjectWatcherInterceptor$interceptPerform(com.kaspersky.components.kautomator.intercept.interaction.UiObjectInteraction, com.kaspersky.components.kautomator.intercept.operation.UiOperation((androidx.test.uiautomator.UiObject2)))/action) is performed

`action` - responsible for performing an activity (action) on the given [interaction](intercept-perform.md#com.kaspersky.kaspresso.interceptors.watcher.kautomator.impl.logging.LoggingObjectWatcherInterceptor$interceptPerform(com.kaspersky.components.kautomator.intercept.interaction.UiObjectInteraction, com.kaspersky.components.kautomator.intercept.operation.UiOperation((androidx.test.uiautomator.UiObject2)))/interaction)
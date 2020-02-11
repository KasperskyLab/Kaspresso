[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.kautomator](../index.md) / [KautomatorWatcherInterceptor](index.md) / [interceptCheck](./intercept-check.md)

# interceptCheck

`abstract fun interceptCheck(interaction: `[`Interaction`](index.md#Interaction)`, assertion: `[`Assertion`](index.md#Assertion)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Called to do some stuff before [UiInteraction.check](#) is actually called.

### Parameters

`interaction` - a Kautomator UiInteraction on which [assertion](intercept-check.md#com.kaspersky.kaspresso.interceptors.watcher.kautomator.KautomatorWatcherInterceptor$interceptCheck(com.kaspersky.kaspresso.interceptors.watcher.kautomator.KautomatorWatcherInterceptor.Interaction, com.kaspersky.kaspresso.interceptors.watcher.kautomator.KautomatorWatcherInterceptor.Assertion)/assertion) is performed

`assertion` - responsible for performing an activity (assertion) on the given [interaction](intercept-check.md#com.kaspersky.kaspresso.interceptors.watcher.kautomator.KautomatorWatcherInterceptor$interceptCheck(com.kaspersky.kaspresso.interceptors.watcher.kautomator.KautomatorWatcherInterceptor.Interaction, com.kaspersky.kaspresso.interceptors.watcher.kautomator.KautomatorWatcherInterceptor.Assertion)/interaction)
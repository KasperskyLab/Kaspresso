[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.kautomator](../index.md) / [KautomatorWatcherInterceptor](index.md) / [interceptPerform](./intercept-perform.md)

# interceptPerform

`abstract fun interceptPerform(interaction: Interaction, action: Action): Unit`

Called to do some stuff before [UiInteraction.perform](#) is actually called.

### Parameters

`interaction` - a Kautomator UiInteraction on which [action](intercept-perform.md#com.kaspersky.kaspresso.interceptors.watcher.kautomator.KautomatorWatcherInterceptor$interceptPerform(com.kaspersky.kaspresso.interceptors.watcher.kautomator.KautomatorWatcherInterceptor.Interaction, com.kaspersky.kaspresso.interceptors.watcher.kautomator.KautomatorWatcherInterceptor.Action)/action) is performed

`action` - responsible for performing an activity (action) on the given [interaction](intercept-perform.md#com.kaspersky.kaspresso.interceptors.watcher.kautomator.KautomatorWatcherInterceptor$interceptPerform(com.kaspersky.kaspresso.interceptors.watcher.kautomator.KautomatorWatcherInterceptor.Interaction, com.kaspersky.kaspresso.interceptors.watcher.kautomator.KautomatorWatcherInterceptor.Action)/interaction)
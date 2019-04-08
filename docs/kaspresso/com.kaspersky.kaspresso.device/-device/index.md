[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device](../index.md) / [Device](./index.md)

# Device

`object Device`

A provider of managers for all off-screen work.

### Properties

| Name | Summary |
|---|---|
| [accessibility](accessibility.md) | `var accessibility: `[`Accessibility`](../../com.kaspersky.kaspresso.device.accessibility/-accessibility/index.md)<br>Holds a reference to an implementation of [Accessibility](../../com.kaspersky.kaspresso.device.accessibility/-accessibility/index.md) interface, held by [Configurator](../../com.kaspersky.kaspresso.configurator/-configurator/index.md). |
| [activities](activities.md) | `val activities: `[`Activities`](../../com.kaspersky.kaspresso.device.activities/-activities/index.md)<br>Holds a reference to an implementation of [Activities](../../com.kaspersky.kaspresso.device.activities/-activities/index.md) interface, held by [Configurator](../../com.kaspersky.kaspresso.configurator/-configurator/index.md). |
| [apps](apps.md) | `val apps: `[`Apps`](../../com.kaspersky.kaspresso.device.apps/-apps/index.md)<br>Holds an implementation of [Apps](../../com.kaspersky.kaspresso.device.apps/-apps/index.md) interface. If it was not specified in [Configurator.Builder](../../com.kaspersky.kaspresso.configurator/-configurator/-builder/index.md), the default implementation is used. |
| [context](context.md) | `val context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)<br>A not caching property to get [Context](https://developer.android.com/reference/android/content/Context.html). |
| [exploit](exploit.md) | `var exploit: `[`Exploit`](../../com.kaspersky.kaspresso.device.exploit/-exploit/index.md)<br>Holds a reference to an implementation of [Exploit](../../com.kaspersky.kaspresso.device.exploit/-exploit/index.md) interface, held by [Configurator](../../com.kaspersky.kaspresso.configurator/-configurator/index.md). |
| [files](files.md) | `var files: `[`Files`](../../com.kaspersky.kaspresso.device.files/-files/index.md)<br>Holds a reference to an implementation of [Files](../../com.kaspersky.kaspresso.device.files/-files/index.md) interface, held by [Configurator](../../com.kaspersky.kaspresso.configurator/-configurator/index.md). |
| [internet](internet.md) | `var internet: `[`Internet`](../../com.kaspersky.kaspresso.device.internet/-internet/index.md)<br>Holds a reference to an implementation of [Internet](../../com.kaspersky.kaspresso.device.internet/-internet/index.md) interface, held by [Configurator](../../com.kaspersky.kaspresso.configurator/-configurator/index.md). |
| [permissions](permissions.md) | `var permissions: `[`Permissions`](../../com.kaspersky.kaspresso.device.permissions/-permissions/index.md)<br>Holds a reference to an implementation of [Permissions](../../com.kaspersky.kaspresso.device.permissions/-permissions/index.md) interface, held by [Configurator](../../com.kaspersky.kaspresso.configurator/-configurator/index.md). |
| [screenshots](screenshots.md) | `var screenshots: `[`Screenshots`](../../com.kaspersky.kaspresso.device.screenshots/-screenshots/index.md)<br>Holds a reference to an implementation of [Screenshots](../../com.kaspersky.kaspresso.device.screenshots/-screenshots/index.md) interface, held by [Configurator](../../com.kaspersky.kaspresso.configurator/-configurator/index.md). |
| [targetContext](target-context.md) | `val targetContext: `[`Context`](https://developer.android.com/reference/android/content/Context.html)<br>A not caching property to get target [Context](https://developer.android.com/reference/android/content/Context.html). |
| [uiDevice](ui-device.md) | `val uiDevice: UiDevice`<br>A property to get an instance of [UiDevice](#). |

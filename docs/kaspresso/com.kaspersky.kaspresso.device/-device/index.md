[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device](../index.md) / [Device](./index.md)

# Device

`data class Device`

The provider of managers for all off-screen work.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Device(apps: `[`Apps`](../../com.kaspersky.kaspresso.device.apps/-apps/index.md)`, activities: `[`Activities`](../../com.kaspersky.kaspresso.device.activities/-activities/index.md)`, files: `[`Files`](../../com.kaspersky.kaspresso.device.files/-files/index.md)`, network: `[`Network`](../../com.kaspersky.kaspresso.device.network/-network/index.md)`, phone: `[`Phone`](../../com.kaspersky.kaspresso.device.phone/-phone/index.md)`, location: `[`Location`](../../com.kaspersky.kaspresso.device.location/-location/index.md)`, keyboard: `[`Keyboard`](../../com.kaspersky.kaspresso.device.keyboard/-keyboard/index.md)`, screenshots: `[`Screenshots`](../../com.kaspersky.kaspresso.device.screenshots/-screenshots/index.md)`, accessibility: `[`Accessibility`](../../com.kaspersky.kaspresso.device.accessibility/-accessibility/index.md)`, permissions: `[`Permissions`](../../com.kaspersky.kaspresso.device.permissions/-permissions/index.md)`, hackPermissions: `[`HackPermissions`](../../com.kaspersky.kaspresso.device.permissions/-hack-permissions/index.md)`, exploit: `[`Exploit`](../../com.kaspersky.kaspresso.device.exploit/-exploit/index.md)`, language: `[`Language`](../../com.kaspersky.kaspresso.device.languages/-language/index.md)`, logcat: `[`Logcat`](../../com.kaspersky.kaspresso.device.logcat/-logcat/index.md)`)`<br>The provider of managers for all off-screen work. |

### Properties

| Name | Summary |
|---|---|
| [accessibility](accessibility.md) | `val accessibility: `[`Accessibility`](../../com.kaspersky.kaspresso.device.accessibility/-accessibility/index.md)<br>Holds the reference to the implementation of [Accessibility](../../com.kaspersky.kaspresso.device.accessibility/-accessibility/index.md) interface. |
| [activities](activities.md) | `val activities: `[`Activities`](../../com.kaspersky.kaspresso.device.activities/-activities/index.md)<br>Holds the reference to the implementation of [Activities](../../com.kaspersky.kaspresso.device.activities/-activities/index.md) interface. |
| [apps](apps.md) | `val apps: `[`Apps`](../../com.kaspersky.kaspresso.device.apps/-apps/index.md)<br>Holds the reference to the implementation of [Apps](../../com.kaspersky.kaspresso.device.apps/-apps/index.md) interface. |
| [context](context.md) | `val context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)<br>A not caching property to get [Context](https://developer.android.com/reference/android/content/Context.html). |
| [exploit](exploit.md) | `val exploit: `[`Exploit`](../../com.kaspersky.kaspresso.device.exploit/-exploit/index.md)<br>Holds the reference to the implementation of [Exploit](../../com.kaspersky.kaspresso.device.exploit/-exploit/index.md) interface. |
| [files](files.md) | `val files: `[`Files`](../../com.kaspersky.kaspresso.device.files/-files/index.md)<br>Holds the reference to the implementation of [Files](../../com.kaspersky.kaspresso.device.files/-files/index.md) interface. |
| [hackPermissions](hack-permissions.md) | `val hackPermissions: `[`HackPermissions`](../../com.kaspersky.kaspresso.device.permissions/-hack-permissions/index.md)<br>Holds the reference to the implementation of [HackPermissions](../../com.kaspersky.kaspresso.device.permissions/-hack-permissions/index.md) interface. |
| [keyboard](keyboard.md) | `val keyboard: `[`Keyboard`](../../com.kaspersky.kaspresso.device.keyboard/-keyboard/index.md)<br>Holds the reference to the implementation of [Keyboard](../../com.kaspersky.kaspresso.device.keyboard/-keyboard/index.md) interface. |
| [language](language.md) | `val language: `[`Language`](../../com.kaspersky.kaspresso.device.languages/-language/index.md)<br>Holds the reference to the implementation of [Language](../../com.kaspersky.kaspresso.device.languages/-language/index.md) interface. |
| [location](location.md) | `val location: `[`Location`](../../com.kaspersky.kaspresso.device.location/-location/index.md)<br>Holds the reference to the implementation of [Location](../../com.kaspersky.kaspresso.device.location/-location/index.md) interface. |
| [logcat](logcat.md) | `val logcat: `[`Logcat`](../../com.kaspersky.kaspresso.device.logcat/-logcat/index.md)<br>Holds the reference to the implementation of [Logcat](../../com.kaspersky.kaspresso.device.logcat/-logcat/index.md) interface. |
| [network](network.md) | `val network: `[`Network`](../../com.kaspersky.kaspresso.device.network/-network/index.md)<br>Holds the reference to the implementation of [Network](../../com.kaspersky.kaspresso.device.network/-network/index.md) interface. |
| [permissions](permissions.md) | `val permissions: `[`Permissions`](../../com.kaspersky.kaspresso.device.permissions/-permissions/index.md)<br>Holds the reference to the implementation of [Permissions](../../com.kaspersky.kaspresso.device.permissions/-permissions/index.md) interface. |
| [phone](phone.md) | `val phone: `[`Phone`](../../com.kaspersky.kaspresso.device.phone/-phone/index.md)<br>Holds the reference to the implementation of [Phone](../../com.kaspersky.kaspresso.device.phone/-phone/index.md) interface. |
| [screenshots](screenshots.md) | `val screenshots: `[`Screenshots`](../../com.kaspersky.kaspresso.device.screenshots/-screenshots/index.md)<br>Holds the reference to the implementation of [Screenshots](../../com.kaspersky.kaspresso.device.screenshots/-screenshots/index.md) interface. |
| [targetContext](target-context.md) | `val targetContext: `[`Context`](https://developer.android.com/reference/android/content/Context.html)<br>A not caching property to get target [Context](https://developer.android.com/reference/android/content/Context.html). |
| [uiDevice](ui-device.md) | `val uiDevice: UiDevice`<br>A property to get the instance of [UiDevice](#). |

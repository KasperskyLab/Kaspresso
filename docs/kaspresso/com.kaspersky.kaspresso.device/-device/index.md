[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device](../index.md) / [Device](./index.md)

# Device

`data class Device`

The provider of managers for all off-screen work.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The provider of managers for all off-screen work.`Device(apps: `[`Apps`](../../com.kaspersky.kaspresso.device.apps/-apps/index.md)`, activities: `[`Activities`](../../com.kaspersky.kaspresso.device.activities/-activities/index.md)`, files: `[`Files`](../../com.kaspersky.kaspresso.device.files/-files/index.md)`, network: `[`Network`](../../com.kaspersky.kaspresso.device.network/-network/index.md)`, phone: `[`Phone`](../../com.kaspersky.kaspresso.device.phone/-phone/index.md)`, location: `[`Location`](../../com.kaspersky.kaspresso.device.location/-location/index.md)`, keyboard: `[`Keyboard`](../../com.kaspersky.kaspresso.device.keyboard/-keyboard/index.md)`, screenshots: `[`Screenshots`](../../com.kaspersky.kaspresso.device.screenshots/-screenshots/index.md)`, accessibility: `[`Accessibility`](../../com.kaspersky.kaspresso.device.accessibility/-accessibility/index.md)`, permissions: `[`Permissions`](../../com.kaspersky.kaspresso.device.permissions/-permissions/index.md)`, hackPermissions: `[`HackPermissions`](../../com.kaspersky.kaspresso.device.permissions/-hack-permissions/index.md)`, exploit: `[`Exploit`](../../com.kaspersky.kaspresso.device.exploit/-exploit/index.md)`, language: `[`Language`](../../com.kaspersky.kaspresso.device.languages/-language/index.md)`, logcat: `[`Logcat`](../../com.kaspersky.kaspresso.device.logcat/-logcat/index.md)`)` |

### Properties

| Name | Summary |
|---|---|
| [accessibility](accessibility.md) | Holds the reference to the implementation of [Accessibility](../../com.kaspersky.kaspresso.device.accessibility/-accessibility/index.md) interface.`val accessibility: `[`Accessibility`](../../com.kaspersky.kaspresso.device.accessibility/-accessibility/index.md) |
| [activities](activities.md) | Holds the reference to the implementation of [Activities](../../com.kaspersky.kaspresso.device.activities/-activities/index.md) interface.`val activities: `[`Activities`](../../com.kaspersky.kaspresso.device.activities/-activities/index.md) |
| [apps](apps.md) | Holds the reference to the implementation of [Apps](../../com.kaspersky.kaspresso.device.apps/-apps/index.md) interface.`val apps: `[`Apps`](../../com.kaspersky.kaspresso.device.apps/-apps/index.md) |
| [context](context.md) | A not caching property to get [Context](https://developer.android.com/reference/android/content/Context.html).`val context: `[`Context`](https://developer.android.com/reference/android/content/Context.html) |
| [exploit](exploit.md) | Holds the reference to the implementation of [Exploit](../../com.kaspersky.kaspresso.device.exploit/-exploit/index.md) interface.`val exploit: `[`Exploit`](../../com.kaspersky.kaspresso.device.exploit/-exploit/index.md) |
| [files](files.md) | Holds the reference to the implementation of [Files](../../com.kaspersky.kaspresso.device.files/-files/index.md) interface.`val files: `[`Files`](../../com.kaspersky.kaspresso.device.files/-files/index.md) |
| [hackPermissions](hack-permissions.md) | Holds the reference to the implementation of [HackPermissions](../../com.kaspersky.kaspresso.device.permissions/-hack-permissions/index.md) interface.`val hackPermissions: `[`HackPermissions`](../../com.kaspersky.kaspresso.device.permissions/-hack-permissions/index.md) |
| [keyboard](keyboard.md) | Holds the reference to the implementation of [Keyboard](../../com.kaspersky.kaspresso.device.keyboard/-keyboard/index.md) interface.`val keyboard: `[`Keyboard`](../../com.kaspersky.kaspresso.device.keyboard/-keyboard/index.md) |
| [language](language.md) | Holds the reference to the implementation of [Language](../../com.kaspersky.kaspresso.device.languages/-language/index.md) interface.`val language: `[`Language`](../../com.kaspersky.kaspresso.device.languages/-language/index.md) |
| [location](location.md) | Holds the reference to the implementation of [Location](../../com.kaspersky.kaspresso.device.location/-location/index.md) interface.`val location: `[`Location`](../../com.kaspersky.kaspresso.device.location/-location/index.md) |
| [logcat](logcat.md) | Holds the reference to the implementation of [Logcat](../../com.kaspersky.kaspresso.device.logcat/-logcat/index.md) interface.`val logcat: `[`Logcat`](../../com.kaspersky.kaspresso.device.logcat/-logcat/index.md) |
| [network](network.md) | Holds the reference to the implementation of [Network](../../com.kaspersky.kaspresso.device.network/-network/index.md) interface.`val network: `[`Network`](../../com.kaspersky.kaspresso.device.network/-network/index.md) |
| [permissions](permissions.md) | Holds the reference to the implementation of [Permissions](../../com.kaspersky.kaspresso.device.permissions/-permissions/index.md) interface.`val permissions: `[`Permissions`](../../com.kaspersky.kaspresso.device.permissions/-permissions/index.md) |
| [phone](phone.md) | Holds the reference to the implementation of [Phone](../../com.kaspersky.kaspresso.device.phone/-phone/index.md) interface.`val phone: `[`Phone`](../../com.kaspersky.kaspresso.device.phone/-phone/index.md) |
| [screenshots](screenshots.md) | Holds the reference to the implementation of [Screenshots](../../com.kaspersky.kaspresso.device.screenshots/-screenshots/index.md) interface.`val screenshots: `[`Screenshots`](../../com.kaspersky.kaspresso.device.screenshots/-screenshots/index.md) |
| [targetContext](target-context.md) | A not caching property to get target [Context](https://developer.android.com/reference/android/content/Context.html).`val targetContext: `[`Context`](https://developer.android.com/reference/android/content/Context.html) |
| [uiDevice](ui-device.md) | A property to get the instance of [UiDevice](#).`val uiDevice: UiDevice` |

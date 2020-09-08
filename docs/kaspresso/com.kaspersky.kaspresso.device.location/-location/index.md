[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.location](../index.md) / [Location](./index.md)

# Location

`interface Location`

The interface to work with device's location.

Required: Started AdbServer
    1. Download a file "kaspresso/artifacts/adbserver-desktop.jar"
    2. Start AdbServer =&gt; input in cmd "java jar path_to_file/adbserver-desktop.jar"
Methods demanding to use AdbServer in the default implementation of this interface are marked.
    But nobody can't deprecate you to write implementation that doesn't require AdbServer.

### Functions

| Name | Summary |
|---|---|
| [disableGps](disable-gps.md) | Disables GPS on the device.`abstract fun disableGps(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [enableGps](enable-gps.md) | Enables GPS on the device.`abstract fun enableGps(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [setLocation](set-location.md) | Sets current location.`abstract fun setLocation(lat: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, lon: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [LocationImpl](../-location-impl/index.md) | The implementation of the [Location](./index.md) interface.`class LocationImpl : `[`Location`](./index.md) |

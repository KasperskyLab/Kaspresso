[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.location](../index.md) / [Location](./index.md)

# Location

`interface Location`

The interface to work with device's location.

Required: Started AdbServer
    1. Download a file "kaspresso/artifacts/desktop.jar"
    2. Start AdbServer =&gt; input in cmd "java jar path_to_file/desktop.jar"
Methods demanding to use AdbServer in the default implementation of this interface are marked.
    But nobody can't deprecate you to write implementation that doesn't require AdbServer.

### Functions

| Name | Summary |
|---|---|
| [disableGps](disable-gps.md) | Disables GPS on the device.`abstract fun disableGps(): Unit` |
| [enableGps](enable-gps.md) | Enables GPS on the device.`abstract fun enableGps(): Unit` |
| [setLocation](set-location.md) | Sets current location.`abstract fun setLocation(lat: Double, lon: Double): Unit` |

### Inheritors

| Name | Summary |
|---|---|
| [LocationImpl](../-location-impl/index.md) | The implementation of the [Location](./index.md) interface.`class LocationImpl : `[`Location`](./index.md) |

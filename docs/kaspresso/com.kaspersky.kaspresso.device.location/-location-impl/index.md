[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.location](../index.md) / [LocationImpl](./index.md)

# LocationImpl

`class LocationImpl : `[`Location`](../-location/index.md)

The implementation of the [Location](../-location/index.md) interface.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The implementation of the [Location](../-location/index.md) interface.`LocationImpl(adbServer: `[`AdbServer`](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [disableGps](disable-gps.md) | Disables GPS on the device.`fun disableGps(): Unit` |
| [enableGps](enable-gps.md) | Enables GPS on the device.`fun enableGps(): Unit` |
| [setLocation](set-location.md) | Sets current location.`fun setLocation(lat: Double, lon: Double): Unit` |

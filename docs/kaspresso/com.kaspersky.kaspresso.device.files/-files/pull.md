[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.files](../index.md) / [Files](index.md) / [pull](./pull.md)

# pull

`abstract fun pull(devicePath: String, serverPath: String = ""): Unit`

Performs adb pull.

Required Permissions: INTERNET.

### Parameters

`devicePath` - a file path relative to the device directory.

`serverPath` - a path to copy. (If empty - pulls in adbServer directory (folder with file "desktop.jar"))
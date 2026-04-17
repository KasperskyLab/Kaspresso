package com.kaspersky.kaspresso.device.bluetooth

/**
 * The interface to work with Bluetooth settings.
 *
 * Required: Started AdbServer
 *     1. Download a file "kaspresso/artifacts/adbserver-desktop.jar"
 *     2. Start AdbServer => input in cmd "java -jar path_to_file/adbserver-desktop.jar"
 * Methods demanding to use AdbServer in the default implementation of this interface are marked.
 *     But nobody can't deprecate you to write implementation that doesn't require AdbServer.
 */
interface Bluetooth {

    /**
     * Enables Bluetooth on the device.
     *
     * Tries in order:
     * 1. Android API ([android.bluetooth.BluetoothAdapter.enable]) — requires
     *    [android.Manifest.permission.BLUETOOTH_ADMIN] below API 31, or
     *    [android.Manifest.permission.BLUETOOTH_CONNECT] on API 31–32.
     *    Restricted for third-party apps on API 33+ (TIRAMISU) and above.
     * 2. ADB `svc bluetooth enable` via AdbServer (available since API 17).
     * 3. Android Settings UI as a last resort.
     */
    fun enable()

    /**
     * Disables Bluetooth on the device.
     *
     * Tries in order:
     * 1. Android API ([android.bluetooth.BluetoothAdapter.disable]) — requires
     *    [android.Manifest.permission.BLUETOOTH_ADMIN] below API 31, or
     *    [android.Manifest.permission.BLUETOOTH_CONNECT] on API 31–32.
     *    Restricted for third-party apps on API 33+ (TIRAMISU) and above.
     * 2. ADB `svc bluetooth disable` via AdbServer (available since API 17).
     * 3. Android Settings UI as a last resort.
     */
    fun disable()
}

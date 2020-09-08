package com.kaspersky.kaspresso.device.server

@Suppress("MaxLineLength")
enum class AdbServerLogsType {

    // All logs from AdbServer lib (device part) including class and method invoke info (be aware that this possibility uses `Throwable().stackTrace`!)
    // Example:
    // 2020-09-07 18:24:03.258 12273-12323/com.kaspersky.kaspressample D/KASPRESSO_ADBSERVER: class=ConnectionClientImplBySocket method=tryConnect message: Start the process
    // 2020-09-07 18:24:03.261 12273-12323/com.kaspersky.kaspressample D/KASPRESSO_ADBSERVER: class=ConnectionMaker method=connect message: Start a connection establishment. The current state=DISCONNECTED
    // 2020-09-07 18:24:03.262 12273-12323/com.kaspersky.kaspressample D/KASPRESSO_ADBSERVER: class=ConnectionMaker method=connect message: The current state=CONNECTING
    // 2020-09-07 18:24:03.263 12273-12323/com.kaspersky.kaspressample D/KASPRESSO_ADBSERVER: class=DesktopDeviceSocketConnectionForwardImpl$getDeviceSocketLoad$1 method=invoke message: Started
    DEBUG_FULL,

    // Logs from AdbServer lib (device part) except debug logs
    // Example:
    // 2020-09-07 18:21:59.152 11498-11527/com.kaspersky.kaspressample I/KASPRESSO_ADBSERVER: User called a start of connection to Desktop
    // 2020-09-07 18:21:59.152 11498-11554/com.kaspersky.kaspressample I/KASPRESSO_ADBSERVER: WatchdogThread is started from Device to Desktop
    INFO_ONLY,

    // Obviously, no logs from AdbServer lib (device part)
    NO_LOGS
}

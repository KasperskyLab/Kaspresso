package com.kaspersky.adbserver.desdevconnection

object DesktopDeviceSocketConnectionFactory {

    fun getSockets(
        desktopDeviceSocketConnectionType: DesktopDeviceSocketConnectionType
    ): DesktopDeviceSocketConnection {
        return when (desktopDeviceSocketConnectionType) {
            DesktopDeviceSocketConnectionType.FORWARD -> DesktopDeviceSocketConnectionForwardImpl()
            DesktopDeviceSocketConnectionType.REVERSE -> throw UnsupportedOperationException("Please implement REVERSE DesktopDeviceSocketConnection")
        }
    }
}

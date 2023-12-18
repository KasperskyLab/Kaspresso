package com.kaspersky.adbserver.desktop

import com.kaspersky.adbserver.common.api.ExecutorResultStatus
import com.kaspersky.adbserver.common.log.LoggerFactory
import com.kaspersky.adbserver.common.log.logger.DesktopLogger
import java.util.concurrent.atomic.AtomicBoolean
import java.util.regex.Pattern
import kotlin.concurrent.thread

class Desktop(
    private val cmdCommandPerformer: CmdCommandPerformer,
    private val adbCommandPerformer: AdbCommandPerformer,
    private val presetEmulators: List<String>,
    private val adbServerPort: String?,
    private val logger: DesktopLogger,
    private val adbPath: String
) {

    companion object {
        private const val PAUSE_MS = 500L
        private val DEVICE_PATTERN = Pattern.compile("^([a-zA-Z0-9\\-:.]+)(\\s+)(device)")
    }

    private val devices: MutableCollection<DeviceMirror> = mutableListOf()
    private var isRunning = AtomicBoolean(false)

    /**
     * Start Desktop server.
     * Blocking current thread while server working
     * @throws IllegalStateException - if server already running
     */
    fun startDevicesObservingSync() {
        if (!isRunning.compareAndSet(false, true)) error("Desktop already running")
        startDevicesObservingInternal()
    }

    /**
     * Start Desktop server asynchronously
     * @throws IllegalStateException - if server already running
     */
    fun startDevicesObservingAsync() {
        if (!isRunning.compareAndSet(false, true)) error("Desktop already running")
        thread {
            startDevicesObservingInternal()
        }
    }

    /**
     * Stop Desktop server
     * @throws IllegalStateException - if server already stopped
     */
    fun stopDevicesObserving() {
        if (!isRunning.compareAndSet(true, false)) error("Desktop already stopped")
    }

    private fun startDevicesObservingInternal() {
        logger.d("start")
        while (isRunning.get()) {
            val namesOfAttachedDevicesByAdb = getAttachedDevicesByAdb()
            namesOfAttachedDevicesByAdb.forEach { deviceName ->
                if (devices.find { client -> client.deviceName == deviceName } == null) {
                    logger.i("New device has been found: $deviceName. Initialize connection to the device...")
                    val deviceMirror =
                        DeviceMirror.create(
                            cmdCommandPerformer,
                            adbCommandPerformer,
                            deviceName,
                            adbServerPort,
                            LoggerFactory.getDesktopLoggerReflectingDevice(logger, deviceName),
                            adbPath
                        )
                    deviceMirror.startConnectionToDevice()
                    devices += deviceMirror
                }
            }
            devices.removeIf { client ->
                if (client.deviceName !in namesOfAttachedDevicesByAdb) {
                    logger.i("Adb connection to ${client.deviceName} has been missed. Stop connection.")
                    client.stopConnectionToDevice()
                    return@removeIf true
                } else {
                    return@removeIf false
                }
            }
            Thread.sleep(PAUSE_MS)
        }

        devices.forEach { client ->
            client.stopConnectionToDevice()
        }
        devices.clear()
    }

    private fun getAttachedDevicesByAdb(): List<String> {
        val commandResult = adbCommandPerformer.perform("devices")
        if (commandResult.status != ExecutorResultStatus.SUCCESS) {
            return emptyList()
        }
        val adbDevicesCommandResult: String = commandResult.description
        return adbDevicesCommandResult.lines()
            .asSequence()
            .map { DEVICE_PATTERN.matcher(it) }
            .filter { matcher -> matcher.find() }
            .map { matcher -> matcher.group(1) }
            .filter { foundEmulator -> presetEmulators.isEmpty() || presetEmulators.contains(foundEmulator) }
            .toList()
    }
}

package com.kaspersky.adbserver.desktop

import com.kaspersky.adbserver.common.api.ExecutorResultStatus
import com.kaspersky.adbserver.common.log.LoggerFactory
import com.kaspersky.adbserver.common.log.logger.DesktopLogger
import java.util.concurrent.atomic.AtomicBoolean
import java.util.regex.Pattern
import kotlin.concurrent.thread

class Desktop(
    desktopName: String,
    private val presetEmulators: List<String>,
    private val adbServerPort: String?,
    private val logger: DesktopLogger
) {

    companion object {
        private const val PAUSE_MS = 500L
    }

    private val cmdCommandPerformer = CmdCommandPerformer(desktopName)
    private val devices: MutableCollection<DeviceMirror> = mutableListOf()
    private var isRunning = AtomicBoolean(false)

    fun startDevicesObservingSync() {
        if (!isRunning.compareAndSet(false, true)) error("Desktop already running")
        startDevicesObservingInternal()
    }

    fun startDevicesObservingAsync() {
        if (!isRunning.compareAndSet(false, true)) error("Desktop already running")
        thread {
            startDevicesObservingInternal()
        }
    }

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
                            deviceName,
                            adbServerPort,
                            LoggerFactory.getDesktopLoggerReflectingDevice(logger, deviceName)
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
        val pattern = Pattern.compile("^([a-zA-Z0-9\\-:.]+)(\\s+)(device)")
        val commandResult = cmdCommandPerformer.perform("adb devices")
        if (commandResult.status != ExecutorResultStatus.SUCCESS) {
            return emptyList()
        }
        val adbDevicesCommandResult: String = commandResult.description
        return adbDevicesCommandResult.lines()
            .asSequence()
            .map { pattern.matcher(it) }
            .filter { matcher -> matcher.find() }
            .map { matcher -> matcher.group(1) }
            .filter { foundEmulator -> presetEmulators.isEmpty() || presetEmulators.contains(foundEmulator) }
            .toList()
    }
}

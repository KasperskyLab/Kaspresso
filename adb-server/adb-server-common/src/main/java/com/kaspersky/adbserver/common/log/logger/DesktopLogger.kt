package com.kaspersky.adbserver.common.log.logger

class DesktopLogger(
    private val logger: Logger,
    internal val logLevel: LogLevel,
    internal val desktopName: String
) : Logger by logger

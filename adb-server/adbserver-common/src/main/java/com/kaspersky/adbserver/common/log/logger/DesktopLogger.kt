package com.kaspersky.adbserver.common.log.logger

class DesktopLogger(
    private val logger: Logger,
    internal val logPolicy: LogPolicy,
    internal val desktopName: String
) : Logger by logger

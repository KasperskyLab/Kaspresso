package com.kaspresky.adbserver.log.logger

import com.kaspresky.adbserver.log.fulllogger.LogPolicy

class DesktopLogger(
    private val logger: Logger,
    internal val logPolicy: LogPolicy,
    internal val desktopName: String
) : Logger by logger
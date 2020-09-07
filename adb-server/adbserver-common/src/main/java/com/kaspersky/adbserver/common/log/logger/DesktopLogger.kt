package com.kaspersky.adbserver.common.log.logger

import com.kaspersky.adbserver.common.log.fulllogger.LogPolicy

class DesktopLogger(
    private val logger: Logger,
    internal val logPolicy: LogPolicy,
    internal val desktopName: String
) : Logger by logger

package com.kaspersky.kaspresso.logger

import com.kaspersky.kaspresso.configurator.Configurator

object KLogger: UiTestLogger {

    private val logger: UiTestLogger = Configurator.outerLogger

    override fun i(text: String) = logger.i(text)
    override fun d(text: String) = logger.d(text)
    override fun e(text: String) = logger.e(text)

    override fun i(tag: String, text: String) = logger.i(tag, text)
    override fun d(tag: String, text: String) = logger.d(tag, text)
    override fun e(tag: String, text: String) = logger.e(tag, text)
}
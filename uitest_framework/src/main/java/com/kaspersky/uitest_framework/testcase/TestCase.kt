package com.kaspersky.uitest_framework.testcase

import com.kaspersky.uitest_framework.Configuration
import com.kaspersky.uitest_framework.logger.UiTestLogger

/**
 * Created by egor.kurnikov on 03.03.2019
 */

abstract class TestCase{

    private val logger: UiTestLogger = Configuration.logger

    protected fun precondition(description: String, actions: () -> Unit) {
        logger.i(description)
        actions.invoke()
    }

    protected fun step(description: String, actions: () -> Unit) {
        logger.i(description)
        actions.invoke()
    }

    interface ScreensStorage
}
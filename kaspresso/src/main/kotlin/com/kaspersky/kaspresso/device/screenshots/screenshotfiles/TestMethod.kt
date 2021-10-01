package com.kaspersky.kaspresso.device.screenshots.screenshotfiles

@Deprecated(
    "The work with screenshots and relative resource providers was redesigned.\n" +
            "Please migrate to new system of work with resources presented in 'files/resources' folder.\n" +
            "An example of migration is shown in a secondary constructor of 'DocLocScreenshotTestCase'.")
data class TestMethod(val className: String, val methodName: String)

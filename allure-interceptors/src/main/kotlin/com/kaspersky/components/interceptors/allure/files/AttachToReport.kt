package com.kaspersky.components.interceptors.allure.files

import io.qameta.allure.android.AllureAndroidLifecycle
import java.io.File

fun File.attachLogcatToAllureReport(): Unit = AllureAndroidLifecycle.addAttachment(
    name = name,
    stream = this.inputStream(),
    type = "text/plain",
    fileExtension = "txt",
)

fun File.attachViewHierarchyToAllureReport(): Unit = AllureAndroidLifecycle.addAttachment(
    name = name,
    stream = this.inputStream(),
    type = "text/xml",
    fileExtension = "xml",
)

fun File.attachScreenshotToAllureReport(): Unit = AllureAndroidLifecycle.addAttachment(
    name = name,
    stream = this.inputStream(),
    type = "image/png",
    fileExtension = "png",
)

fun File.attachVideoToAllureReport(): Unit = AllureAndroidLifecycle.addAttachment(
    name = name,
    stream = this.inputStream(),
    type = "video/mp4",
    fileExtension = "mp4",
)

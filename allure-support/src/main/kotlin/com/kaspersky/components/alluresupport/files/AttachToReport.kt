package com.kaspersky.components.alluresupport.files

import io.qameta.allure.kotlin.Allure
import java.io.File

fun File.attachLogcatToAllureReport(): Unit = Allure.lifecycle.addAttachment(
    name = name,
    stream = this.inputStream(),
    type = "text/plain",
    fileExtension = "txt",
)

fun File.attachViewHierarchyToAllureReport(): Unit = Allure.lifecycle.addAttachment(
    name = name,
    stream = this.inputStream(),
    type = "text/xml",
    fileExtension = "xml",
)

fun File.attachScreenshotToAllureReport(): Unit = Allure.lifecycle.addAttachment(
    name = name,
    stream = this.inputStream(),
    type = "image/png",
    fileExtension = "png",
)

fun File.attachVideoToAllureReport(): Unit = Allure.lifecycle.addAttachment(
    name = name,
    stream = this.inputStream(),
    type = "video/mp4",
    fileExtension = "mp4",
)

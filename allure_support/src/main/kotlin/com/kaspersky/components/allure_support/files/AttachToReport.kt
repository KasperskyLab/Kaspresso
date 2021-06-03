package com.kaspersky.components.allure_support.files

import com.kaspersky.kaspresso.files.FileExtension
import io.qameta.allure.android.io.*
import io.qameta.allure.espresso.AllureAndroidLifecycle
import java.io.File

fun File.attachLogcatToAllureReport(): Unit = AllureAndroidLifecycle.addAttachment(
    name = name,
    type = TEXT_PLAIN,
    fileExtension = TXT_EXTENSION,
    file = this
)

fun File.attachViewHierarchyToAllureReport(): Unit = AllureAndroidLifecycle.addAttachment(
    name,
    TEXT_XML,
    XML_EXTENSION,
    this
)

fun File.attachScreenshotToAllureReport(): Unit = AllureAndroidLifecycle.addAttachment(
    name = name,
    type = IMAGE_PNG,
    fileExtension = PNG_EXTENSION,
    file = this
)

fun File.attachVideoToAllureReport(): Unit = AllureAndroidLifecycle.addAttachment(
    name = name,
    type = "video/mp4",
    fileExtension = FileExtension.MP4.toString(),
    file = this
)

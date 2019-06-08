package com.kaspersky.kaspresso.device.screenshots.screenshoter.internal

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Looper
import com.kaspersky.kaspresso.device.screenshots.screenshoter.ScreenshotMaker
import com.kaspersky.kaspresso.device.screenshots.screenshoter.external.Chmod
import com.kaspersky.kaspresso.logger.UiTestLogger
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.concurrent.CountDownLatch

internal class InternalScreenshotMaker(
    file: File,
    logger: UiTestLogger
) : ScreenshotMaker(file, logger) {

    /**
     * Takes a screenshot with the specified tag.
     *
     * @param tag Unique tag to further identify the screenshot. Must match [a-zA-Z0-9_-]+.
     * @return the image file that was created
     */
    fun screenshot(activity: Activity, tag: String): File {
        val screenshotFile = obtainScreenshotFile(activity, tag)
        takeScreenshot(screenshotFile, activity)
        return screenshotFile
    }

    private fun takeScreenshot(file: File, activity: Activity) {
        val view = activity.window.decorView
        if (view.width == 0 || view.height == 0) {
            throw IOException(
                "Your view has no height or width. " +
                        "Are you sure ${activity.javaClass.simpleName} is the currently displayed activity?"
            )
        }

        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)

        fillBitmap(activity, bitmap, file)

        BufferedOutputStream(FileOutputStream(file)).use {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
            Chmod.chmodPlusR(file)
        }
        bitmap.recycle()
    }

    private fun fillBitmap(activity: Activity, bitmap: Bitmap, file: File) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            activity.drawToBitmap(bitmap)
            return
        }

        val latch = CountDownLatch(1)
        activity.runOnUiThread {
            try {
                activity.drawToBitmap(bitmap)
            } finally {
                latch.countDown()
            }
        }
        latch.runCatching { await() }
            .onFailure { e ->
                val message = "Unable to get screenshot ${file.absolutePath}"
                logger.e(message)
                throw RuntimeException(message, e)
            }
    }

    private fun Activity.drawToBitmap(bitmap: Bitmap) {
        window.decorView.draw(Canvas(bitmap))
    }
}
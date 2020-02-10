package com.kaspersky.kaspresso.device.screenshots.screenshotmaker

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Looper
import com.kaspersky.kaspresso.device.activities.Activities
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.concurrent.CountDownLatch

/**
 * Captures the view of a current activity
 */
class InternalScreenshotMaker(
    private val activities: Activities
) : ScreenshotMaker {

    companion object {
        const val PICTURE_QUALITY = 100
    }

    override fun takeScreenshot(file: File) {
        val activity = activities.getResumed() ?: throw RuntimeException("There is no resumed activity.")
        val view = activity.window.decorView
        if (view.width == 0 || view.height == 0) {
            throw RuntimeException(
                "The view of ${activity.javaClass.simpleName} has no height or width."
            )
        }

        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)

        fillBitmap(activity, bitmap, file)

        BufferedOutputStream(FileOutputStream(file)).use { outputStream ->
            bitmap.compress(Bitmap.CompressFormat.PNG, PICTURE_QUALITY, outputStream)
            file.setReadable(true, false)
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
            .onFailure { e -> throw RuntimeException("Unable to get screenshot ${file.absolutePath}", e) }
    }

    private fun Activity.drawToBitmap(bitmap: Bitmap) {
        window.decorView.draw(Canvas(bitmap))
    }
}
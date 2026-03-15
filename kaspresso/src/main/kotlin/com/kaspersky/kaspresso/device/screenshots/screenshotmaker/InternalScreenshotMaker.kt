package com.kaspersky.kaspresso.device.screenshots.screenshotmaker

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Looper
import android.util.Log
import android.view.View
import com.kaspersky.kaspresso.device.activities.Activities
import com.kaspersky.kaspresso.params.ScreenshotParams
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.concurrent.CountDownLatch

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

/**
 * Captures the view of a current activity
 */
class InternalScreenshotMaker(
    private val activities: Activities,
    private val params: ScreenshotParams
) : ScreenshotMaker {

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
            bitmap.compress(Bitmap.CompressFormat.PNG, params.quality, outputStream)
            file.setReadable(true, false)
        }
        bitmap.recycle()
    }

    override fun takeFullWindowScreenshot(file: File) {
        val activity = activities.getResumed() ?: throw RuntimeException("There is no resumed activity.")

        val bitmap = fillFullBitmap(activity, file)
        BufferedOutputStream(FileOutputStream(file)).use { outputStream ->
            bitmap.compress(Bitmap.CompressFormat.PNG, params.quality, outputStream)
            file.setReadable(true, false)
        }
        bitmap.recycle()
    }

    private fun fillFullBitmap(activity: Activity, file: File): Bitmap {
        val view = activity.window.decorView.rootView
        val originalHeight = view.height
        var bitmap: Bitmap? = null
        val heightMeasureSpec: Int = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        val widthMeasureSpec: Int = View.MeasureSpec.makeMeasureSpec(view.width, View.MeasureSpec.EXACTLY)
        val latch = CountDownLatch(1)
        if (Looper.myLooper() == Looper.getMainLooper()) {
            return drawBitmap(view)
        }
        activity.runOnUiThread {
            view.measure(widthMeasureSpec, heightMeasureSpec)
            if (originalHeight < view.measuredHeight) {
                try {
                    bitmap = drawBitmap(view)
                } finally {
                    latch.countDown()
                }
            } else {
                latch.countDown()
            }
        }
        latch.runCatching {
            await()
        }
        return bitmap!!
    }

    private fun drawBitmap(view: View): Bitmap {
        view.layout(0, 0, view.measuredWidth, view.measuredHeight)
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val bitmapHolder = Canvas(bitmap)
        view.draw(bitmapHolder)
        return bitmap
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
            } catch (e: Exception) {
                Log.e("InternalScreenshotMaker", "Unable to get screenshot ${file.absolutePath}")
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

package com.kaspersky.kaspresso.internal.visual

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import com.kaspersky.kaspresso.files.extensions.FileExtension
import com.kaspersky.kaspresso.files.resources.ResourceFileNamesProvider
import com.kaspersky.kaspresso.files.resources.ResourcesDirsProvider
import com.kaspersky.kaspresso.files.resources.ResourcesRootDirsProvider
import com.kaspersky.kaspresso.logger.Logger
import com.kaspersky.kaspresso.visual.ScreenshotsComparator
import com.kaspersky.kaspresso.visual.VisualTestParams
import java.io.File
import java.io.FileOutputStream
import kotlin.math.abs

open class DefaultScreenshotsComparator(
    private val visualTestParams: VisualTestParams,
    private val logger: Logger,
    private val resourcesRootDirsProvider: ResourcesRootDirsProvider,
    private val resourcesDirsProvider: ResourcesDirsProvider,
    private val resourceFileNamesProvider: ResourceFileNamesProvider,
) : ScreenshotsComparator {

    @Suppress("MagicNumber")
    override fun compare(originalScreenshot: File, newScreenshot: File): Boolean {
        val decodeOptions = BitmapFactory.Options().apply { inMutable = true }
        val screenshot = BitmapFactory.decodeFile(newScreenshot.absolutePath, decodeOptions)
        val original = BitmapFactory.decodeFile(originalScreenshot.absolutePath, decodeOptions)

        if (original.sameAs(screenshot)) {
            return true
        }

        val width: Int = original.width
        val height: Int = original.height
        val pixelsCount = width * height
        val screenshotPixels = IntArray(pixelsCount)
        val originalPixels = IntArray(pixelsCount)
        val diffPixels = IntArray(pixelsCount)

        screenshot.getPixels(screenshotPixels, 0, width, 0, 0, width, height)
        original.getPixels(originalPixels, 0, width, 0, 0, width, height)

        var totalDelta = 0
        for (pixelIndex in 0 until pixelsCount) {
            val areColorsCorrect = checkColors(screenshotPixels[pixelIndex], originalPixels[pixelIndex])
            if (areColorsCorrect) {
                diffPixels[pixelIndex] = Color.BLACK
            } else {
                totalDelta++
                diffPixels[pixelIndex] = Color.WHITE
            }
        }

        val diffValue = totalDelta * 100.0f / (width * height)
        logger.i("${originalScreenshot.absolutePath} diff is $diffValue")
        if (diffValue > visualTestParams.tolerance) {
            val name = originalScreenshot.name
            processScreenshotDiff(original, diffPixels, "diff_$name")
            return false
        }

        return true
    }

    private fun checkColors(rgb1: Int, rgb2: Int): Boolean {
        val colorTolerance = visualTestParams.colorTolerance
        val r1 = Color.red(rgb1)
        val g1 = Color.green(rgb1)
        val b1 = Color.blue(rgb1)
        val r2 = Color.red(rgb2)
        val g2 = Color.green(rgb2)
        val b2 = Color.blue(rgb2)
        return abs(r1 - r2) <= colorTolerance &&
                abs(g1 - g2) <= colorTolerance &&
                abs(b1 - b2) <= colorTolerance
    }

    protected open fun processScreenshotDiff(original: Bitmap, diffPixels: IntArray, diffName: String): File {
        val width = original.width
        val height = original.height
        val diffBitmap = Bitmap.createBitmap(width, height, original.config)
        diffBitmap.setPixels(diffPixels, 0, width, 0, 0, width, height)
        val scaledBitmap = Bitmap.createScaledBitmap(
            diffBitmap,
            width,
            height,
            false,
        )

        val screenshotDiff = resourcesDirsProvider.provide(resourcesRootDirsProvider.screenshotsDiffRootDir)
            .resolve(resourceFileNamesProvider.getFileName(diffName, FileExtension.PNG.toString()))

        scaledBitmap.compress(
            Bitmap.CompressFormat.PNG,
            QUALITY,
            FileOutputStream(screenshotDiff),
        )

        return screenshotDiff
    }

    companion object {
        private const val QUALITY = 100
    }
}

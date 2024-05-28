package com.kaspersky.kaspresso.internal.visual

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Paint
import com.kaspersky.kaspresso.files.dirs.DirsProvider
import com.kaspersky.kaspresso.files.resources.ResourcesRootDirsProvider
import com.kaspersky.kaspresso.logger.Logger
import com.kaspersky.kaspresso.visual.ScreenshotsComparator
import com.kaspersky.kaspresso.visual.VisualTestParams
import java.io.File
import java.io.FileOutputStream
import kotlin.math.abs


class ScreenshotsComparatorImpl(
    private val visualTestParams: VisualTestParams,
    private val logger: Logger,
    private val dirsProvider: DirsProvider,
    private val resourcesRootDirsProvider: ResourcesRootDirsProvider,
) : ScreenshotsComparator {
    override fun compare(originalScreenshot: File, newScreenshot: File): Boolean {
        val decodeOptions = BitmapFactory.Options().apply {
            inMutable = true
        }
        val screenshot = BitmapFactory.decodeFile(newScreenshot.absolutePath, decodeOptions)
        val original = BitmapFactory.decodeFile(originalScreenshot.absolutePath, decodeOptions)
        val paint = Paint()

        paint.color = Color.BLACK

        val screenshotsSame = original.sameAs(screenshot)
        if (screenshotsSame) {
            return true
        }

        val width: Int = original.width
        val height: Int = original.height
        val pixelsCount = width * height
        val screenshotPixels = IntArray(pixelsCount)
        val originalPixels = IntArray(pixelsCount)
        val binaryDiffPixels = IntArray(pixelsCount)
        val contrastDiffPixels = IntArray(pixelsCount)

        screenshot.getPixels(screenshotPixels, 0, width, 0, 0, width, height)
        original.getPixels(originalPixels, 0, width, 0, 0, width, height)

        var totalDelta = 0
        for (pixelIndex in 0 until pixelsCount) {
            val pixelDiffIsCorrect = verifyPixelDiff(screenshotPixels[pixelIndex], originalPixels[pixelIndex])
            if (pixelDiffIsCorrect) {
                binaryDiffPixels[pixelIndex] = Color.BLACK
                contrastDiffPixels[pixelIndex] = originalPixels[pixelIndex]
            } else {
                totalDelta++
                binaryDiffPixels[pixelIndex] = Color.WHITE
                contrastDiffPixels[pixelIndex] = Color.MAGENTA
            }
        }

        val diff = totalDelta * 100.0f / (width * height)
        logger.i("${originalScreenshot.absolutePath} diff is $diff")
        if (diff > visualTestParams.tolerance) {
            val name = originalScreenshot.name
            processScreenshotDiff(original, binaryDiffPixels, "binary_diff_$name")
            processScreenshotDiff(original, contrastDiffPixels, "contrast_diff_$name")
            return false
        }

        return true
    }

    private fun verifyPixelDiff(rgb1: Int, rgb2: Int): Boolean {
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

    private fun processScreenshotDiff(original: Bitmap, diffPixels: IntArray, diffName: String) {
        val width = original.width
        val height = original.height
        val diffBitmap = Bitmap.createBitmap(width, height, original.config)
        diffBitmap.setPixels(diffPixels, 0, width, 0, 0, width, height)
        val diffDir = dirsProvider.provideNew(resourcesRootDirsProvider.screenshotsDiffRootDir)
        val screenshotDiff = File(diffDir, diffName)
        val scaledBitmap = Bitmap.createScaledBitmap(
            diffBitmap,
            width,
            height,
            false,
        )
        assert(
            scaledBitmap.compress(
                Bitmap.CompressFormat.PNG,
                100,
                FileOutputStream(screenshotDiff),
            )
        )
//        screenshotDiff.delete()
    }
}

package com.kaspersky.kaspresso.testcases.api.testcase

import com.kaspersky.kaspresso.kaspresso.Kaspresso
import io.github.kakaocup.kakao.common.assertions.BaseAssertions
import junit.framework.TestCase.assertEquals
import java.io.File
import java.io.FileOutputStream
import kotlin.math.abs
import kotlin.math.roundToInt


import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Environment
import android.os.SystemClock

private const val ORIGINAL_SCREENSHOTS_DEVICE_DIR = "zen_original_screenshots"
private const val DIFF_SCREENSHOTS_DIR = "zen_diff_screenshots"

// Размеры скриншота
private const val SCREENSHOT_REQUIRED_HEIGHT = 1794
private const val SCREENSHOT_REQUIRED_WIDTH = 1080

// Размеры девайса
private const val SCREENSHOT_DEVICE_HEIGHT = 1920
private const val SCREENSHOT_DEVICE_WIDTH = 1080

/**
 * На разных видеокартах градиенты могут незначительно отличаться.
 * Больше информации можно прочитать тут https://habr.com/ru/articles/754730/
 * Данное значение толерантности допускает расхождение каждой состовляющей цвета (r,g,b) на каждый пиксель.
 */
private const val COLOR_TOLERANCE = 1

/**
 * Задержка перед скриншотом (в мс). Уменьшает вероятность флака.
 * Позволяет дождаться окончания различных анимаций, типа рипла и открытия экрана.
 */
private const val DEFAULT_IDLE_BEFORE_ASSERT: Long = 2_000

private const val REPORT_ATTACHMENT_SCALE_FACTOR = 0.33f
private const val STATUS_BAR_HEIGHT = 65


/**
 * Базовый класс для тест кейсов скриншот тестирования.
 * Все скриншот тесты (которые сравнивают скриншот с эталоном) должны наследоваться от этого класса
 */
abstract class VisualTestCase(
    kaspressoBuilder: Kaspresso.Builder = Kaspresso.Builder.simple(),
    private val idleBeforeAssert: Long = DEFAULT_IDLE_BEFORE_ASSERT,
) : TestCase(kaspressoBuilder) {

    /**
     * Название скриншота по умолчанию, автоматически инкриминируется по мере теста
     * @see assertScreenshot
     */
    private var screenshotId: Long = 0

    /**
     * Допустимое расхождение скриншота полученного во время теста с эталоном в %
     * Например, для скриншота 100x100 (10_000 пикселей) [tolerance] равный 0.5% означает, что 5 пикселей нового
     * скриншота могут отличаться от эталона
     */
    protected open val tolerance = Tolerance.FEATURE.pixelTolerance

    private val originalScreenshotsDeviceDir by lazy {
        File(Environment.getExternalStorageDirectory(), ORIGINAL_SCREENSHOTS_DEVICE_DIR)
    }

    private val originalScreenshotsProjectDir by lazy {
        "${TestArgumentsHolder.originalScreenShotsProjectRootDir}/${this::class.qualifiedName?.replace('.', '/')}"
    }

    private val diffScreenshotsDeviceDir by lazy {
        File(
            Environment.getExternalStorageDirectory().absolutePath,
            DIFF_SCREENSHOTS_DIR,
        )
    }

    fun runScreenshotTest(
        files: List<String> = emptyList(),
        before: (BaseTestContext.() -> Unit)? = null,
        after: (BaseTestContext.() -> Unit)? = null,
        test: TestContext<Unit>.(env: TestEnvironment) -> Unit,
    ) = runTest(
        env = env,
        files = files,
        before = {
            prepare(env)
            before?.invoke(this)
        },
        after = {
            clean(env)
            after?.invoke(this)
        },
        test = test,
    )

    protected fun prepare(env: TestEnvironment) {
        if (env.screenshotTestMode == ScreenshotTestMode.Verify) {
            measureTime("Push screenshot files from project to device") {
                originalScreenshotsDeviceDir.mkdirs()
                device.files.push(
                    originalScreenshotsProjectDir,
                    originalScreenshotsDeviceDir.absolutePath
                )
                diffScreenshotsDeviceDir.mkdirs()
            }
        }
    }

    protected fun clean(env: TestEnvironment) {
        if (env.screenshotTestMode == ScreenshotTestMode.Verify) {
            measureTime("Delete screenshot files from device") {
                deleteDir(originalScreenshotsDeviceDir)
                deleteDir(diffScreenshotsDeviceDir)
            }
        }
    }

    /**
     * Делает скриншот текущего экрана или его части в зависимости от выбранного [screenshotMethod], а затем, в
     * зависимости от выбранного [ScreenshotTestMode] (см. абстрактный метод [mockedEnv]) либо сравнивает скриншот с
     * эталоном с заданным [tolerance], либо записывает новый эталонный скриншот.
     *
     * **Внимание** если вы записываете новый эталонный скриншот то перед пушем в мастер почитайте доку по тестам,
     * там есть определенный процесс
     */
    @SuppressLint("SdCardPath")
    fun TestEnvironment.assertScreenshot(
        screenshotMethod: ScreenshotMethod = ScreenshotMethod.Adb,
        tag: String = "${screenshotId++}",
    ) {
        if (idleBeforeAssert > 0) {
            Screen.idle(idleBeforeAssert)
        }
        lateinit var screenshotFile: File

        when (screenshotMethod) {
            is ScreenshotMethod.Adb -> {
                val path = "/sdcard/$tag.png"
                adbServer.performShell("settings put system pointer_location 0")
                adbServer.performShell("screencap $path")
                adbServer.performShell("settings put system pointer_location 1")
                screenshotFile = File(path)
            }

            is ScreenshotMethod.Kaspresso -> {
                device.screenshots.takeAndApply(tag) {
                    screenshotFile = this
                }
            }

            is ScreenshotMethod.Bitmap -> {
                screenshotFile = resourceFilesProvider.provideScreenshotFile(tag)
                BufferedOutputStream(FileOutputStream(screenshotFile)).use { outputStream ->
                    screenshotMethod.bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                    screenshotFile.setReadable(true, false)
                }
            }
        }

        when (screenshotTestMode) {
            ScreenshotTestMode.Record -> {
                measureTime("Pull screenshot file from device") {
                    screenshotFile.recordScreenshot()
                }
            }

            ScreenshotTestMode.Verify -> {
                measureTime("Compare screenshots") {
                    screenshotFile.verifyScreenshot()
                }
            }
        }
    }

    /**
     * Экстеншен метод для KView.getView(), чтоб скриншотить только конкретную view/viewGroup.
     */
    @Suppress("UnusedReceiverParameter") // BaseAssertions нужен как экстеншн для прямого вызова на KView
    fun BaseAssertions.assertScreenshot(
        env: TestEnvironment,
        tag: String = "${screenshotId++}",
    ): ViewAssertion {
        return ViewAssertion { view, noViewFoundException ->
            val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            ViewScreenshotHelper.fillBitmap(view, bitmap)
            view?.let { env.assertScreenshot(ScreenshotMethod.Bitmap(bitmap), tag) }
                ?: AssertionError("Target view is null")
        }
    }

    /**
     * Экстеншен метод для compose вьюх, чтоб скриншотить только конкретную ноду.
     */
    fun NodeAssertions.assertScreenshot(
        env: TestEnvironment,
        tag: String = "${screenshotId++}",
    ) {
        delegate.check(NodeAssertions.ComposeBaseAssertionType.ASSERT) {
            val bitmap = captureToImage().asAndroidBitmap()
            env.assertScreenshot(ScreenshotMethod.Bitmap(bitmap), tag)
        }
    }

    /**
     * Проверяет высоту и ширину скриншота на соответствие эталонным [SCREENSHOT_REQUIRED_HEIGHT] и
     * [SCREENSHOT_REQUIRED_WIDTH]. После чего загружает скриншот с эмулятора в папку с эталонными скриншотами
     */
    private fun File.recordScreenshot() {
        val bitmapOptions = BitmapFactory.Options()
        bitmapOptions.inJustDecodeBounds = true
        BitmapFactory.decodeFile(absolutePath, bitmapOptions)
        assertEquals(
            device.uiDevice.displayHeight,
            SCREENSHOT_REQUIRED_HEIGHT,
            "Device has wrong resolution. Please use CI test emulator.",
        )
        assertEquals(
            device.uiDevice.displayWidth,
            SCREENSHOT_REQUIRED_WIDTH,
            "Device has wrong resolution. Please use CI test emulator.",
        )
        adbServer.performCmd("mkdir -p $originalScreenshotsProjectDir")
        adbServer.performAdb("pull $absolutePath $originalScreenshotsProjectDir/$name")
        if (TestArgumentsHolder.recordScreenshots) {
            attachScreenshotZipToReport()
        }
        device.files.remove(absolutePath)
    }

    private fun File.verifyScreenshot() {
         val originalFile = File("$originalScreenshotsDeviceDir/${this@VisualTestCase::class.simpleName}", name)
        assert(originalFile.exists()) { "There is no original screenshot to compare with" }

        val decodeOptions = BitmapFactory.Options().apply {
            inMutable = true
        }
        val screenshot = BitmapFactory.decodeFile(absolutePath, decodeOptions)
        val original = BitmapFactory.decodeFile(originalFile.absolutePath, decodeOptions)
        val screenshotCanvas = Canvas(screenshot)
        val originalCanvas = Canvas(original)
        val paint = Paint()

        paint.color = Color.BLACK

        screenshotCanvas.drawBlackRects(paint)
        originalCanvas.drawBlackRects(paint)

        val screenshotsSame = measureTime("Compare Bitmaps with native method") {
            original.sameAs(screenshot)
        }
        if (screenshotsSame) {
            return
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
        if (diff > tolerance) {
            processScreenshotDiff(original, binaryDiffPixels, "binary_diff_$name")
            processScreenshotDiff(original, contrastDiffPixels, "contrast_diff_$name")
            throw ScreenshotsNotMatchException(
                "Скриншот '$name' отличается от эталона на $diff%. Подробности в аттаче."
            )
        }
    }

    private fun processScreenshotDiff(original: Bitmap, diffPixels: IntArray, diffName: String) {
        val width = original.width
        val height = original.height
        val diffBitmap = Bitmap.createBitmap(width, height, original.config)
        diffBitmap.setPixels(diffPixels, 0, width, 0, 0, width, height)
        val screenshotDiff = File(diffScreenshotsDeviceDir, diffName)
        val scaledBitmap = measureTime("Scale bitmap") {
            Bitmap.createScaledBitmap(
                diffBitmap,
                (width * REPORT_ATTACHMENT_SCALE_FACTOR).roundToInt(),
                (height * REPORT_ATTACHMENT_SCALE_FACTOR).roundToInt(),
                false,
            )
        }
        assert(
            scaledBitmap.compress(
                Bitmap.CompressFormat.PNG,
                100,
                FileOutputStream(screenshotDiff),
            )
        )
        screenshotDiff.delete()
    }

    private fun verifyPixelDiff(rgb1: Int, rgb2: Int): Boolean {
        val r1 = Color.red(rgb1)
        val g1 = Color.green(rgb1)
        val b1 = Color.blue(rgb1)
        val r2 = Color.red(rgb2)
        val g2 = Color.green(rgb2)
        val b2 = Color.blue(rgb2)
        return abs(r1 - r2) <= COLOR_TOLERANCE &&
                abs(g1 - g2) <= COLOR_TOLERANCE &&
                abs(b1 - b2) <= COLOR_TOLERANCE
    }

    private fun Canvas.drawBlackRects(paint: Paint) {
        drawRect(Rect(0, 0, 250, STATUS_BAR_HEIGHT), paint)
        drawRect(Rect(SCREENSHOT_DEVICE_WIDTH - 150, 0, SCREENSHOT_DEVICE_WIDTH, STATUS_BAR_HEIGHT), paint)
        drawRect(
            Rect(
                175,
                SCREENSHOT_DEVICE_HEIGHT - 100,
                SCREENSHOT_DEVICE_WIDTH - 175,
                SCREENSHOT_DEVICE_HEIGHT
            ),
            paint
        )
    }

    private fun deleteDir(dir: File?) {
        if (dir == null || !dir.exists()) return

        dir.listFiles()?.forEach { file ->
            if (file.isDirectory) deleteDir(file)
            if (file.isFile) file.delete()
        }
        dir.delete()
    }

    private fun <T> measureTime(message: String, block: () -> T): T {
        val startTime = SystemClock.elapsedRealtime()
        val result = block()
        logger.i("$message: ${SystemClock.elapsedRealtime() - startTime}")
        return result
    }

    /**
     * Ошибка когда скриншот не совпадает с эталоном.
     * Наследуется от семейства асертов для корректного отображения в отчетах Алюра
     */
    protected class ScreenshotsNotMatchException(message: String) : AssertionError(message)
}

sealed interface ScreenshotMethod {

    /** Новый метод записи скриншотов, делает снимок через adb консоль
     * Снимает все, что видит на экране, в том числе системные StatusBar и NavigationBar
     * АХТУНГ! Для тестов видосов нужно использовать старый метод [Kaspresso], иначе тест всегда будет падать
     **/
    object Adb : ScreenshotMethod

    /** Старый метод записи скриншотов, делает снимок корневой View экрана
     * АХТУНГ! На скриншот, сделанный этим методом может попадать не все, что видно на экране.
     * Например, системные StatusBar и NavigationBar или шторка выбора темы в настройках приложения
     * (см. ChangeThemeScreenshotTest). В таких случаях нужно использовать новый метод [Adb]
     **/
    object Kaspresso : ScreenshotMethod

    /**
     * Метод записи скриншотов путём записи в битмапу. Добавлен был для возможности переводить в Bitmap
     * конкретный компонент экрана и его потом уже валидировать. Очень подходит для кейсов, где мы хотим
     * проверить изменение конкретной view или группы view в рамках прохождения сценария, особенно, когда
     * там присутствует скролл.
     */
    class Bitmap(val bitmap: android.graphics.Bitmap) : ScreenshotMethod
}

/**
 * Значения толерантностей
 * [BUG_FEATURE] толерантность для странных фичей у которых постоянно дифф на пустом месте и не ясно почему. Использовать с оглядкой.
 * [FEATURE] толерантность в скриншот тестах приложения (то есть во всех).
 * [DESIGN_SYSTEM] толерантность в скриншот тестах дизайн-системы.
 */
enum class Tolerance(val pixelTolerance: Float) {
    BUG_FEATURE(1f),
    FEATURE(0.5f),
    DESIGN_SYSTEM(0.3f)
}

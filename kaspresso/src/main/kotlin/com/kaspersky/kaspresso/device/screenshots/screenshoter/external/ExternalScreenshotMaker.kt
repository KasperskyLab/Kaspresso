package com.kaspersky.kaspresso.device.screenshots.screenshoter.external

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_WORLD_READABLE
import android.os.Build
import android.os.Environment.getExternalStorageDirectory
import android.support.test.InstrumentationRegistry
import android.support.test.uiautomator.UiDevice
import com.kaspersky.kaspresso.device.screenshots.screenshoter.external.Chmod.Companion.chmodPlusRWX
import com.kaspersky.kaspresso.logger.UiTestLogger
import java.io.File
import java.io.IOException
import java.util.*
import java.util.regex.Pattern

/**
 * Class for capturing spoon-compatible screenshots by uiautomator.
 */
class ExternalScreenshotMaker(
    private val screenshotDir: File,
    private val logger: UiTestLogger
) {

    companion object {

        private const val NAME_SEPARATOR = "_"

        private const val TEST_CASE_CLASS_JUNIT_3 = "android.test.InstrumentationTestCase"
        private const val TEST_CASE_METHOD_JUNIT_3 = "runMethod"

        private const val TEST_CASE_CLASS_JUNIT_4 = "org.junit.runners.model.FrameworkMethod$1"
        private const val TEST_CASE_METHOD_JUNIT_4 = "runReflectiveCall"

        private const val TEST_CASE_CLASS_CUCUMBER_JVM = "cucumber.runtime.model.CucumberFeature"
        private const val TEST_CASE_METHOD_CUCUMBER_JVM = "run"

        private const val EXTENSION = ".png"
        private const val TAG = "ExternalScreenshotMaker"

        private val LOCK = Any()
        private val TAG_VALIDATION = Pattern.compile("[a-zA-Z0-9_-]+")
    }

    /**
     * Holds a set of directories that have been cleared for this test.
     */
    private val clearedOutputDirectories = HashSet<File>()

    /**
     * Takes a screenshot with the specified tag.
     *
     * @param tag Unique tag to further identify the screenshot. Must match [a-zA-Z0-9_-]+.
     * @return the image file that was created
     */
    fun screenshot(tag: String): File {
        val testClass =
            findTestClassTraceElement(Thread.currentThread().stackTrace)
        val className = testClass.className.replace("[^A-Za-z0-9._-]".toRegex(), "_")
        val methodName = testClass.methodName

        return screenshot(tag, className, methodName)
    }

    /**
     * Takes a screenshot with the specified tag.  This version allows the caller to manually specify
     * the test class name and method name.  This is necessary when the screenshot is not called in
     * the traditional manner.
     *.
     * @param tag Unique tag to further identify the screenshot. Must match [a-zA-Z0-9_-]+.
     * @return the image file that was created
     */
    @Suppress("UNUSED")
    fun screenshot(
        tag: String, testClassName: String,
        testMethodName: String
    ): File {
        validateScreenshotTag(tag)
        try {
            val screenshotDirectory =
                obtainScreenshotDirectory(
                    InstrumentationRegistry.getTargetContext().applicationContext,
                    testClassName,
                    testMethodName
                )

            val screenshotName = System.currentTimeMillis().toString() + NAME_SEPARATOR + tag + EXTENSION
            val screenshotFile = screenshotDirectory.resolve(screenshotName)

            takeScreenshot(screenshotFile)

            logger.d(TAG, "Captured screenshot '$tag'.")
            return screenshotFile
        } catch (e: Exception) {
            throw RuntimeException("Unable to capture screenshot.", e)
        }
    }

    /**
     * Takes a screenshot with the specified name. This version allows the caller to manually specify
     * the output directory.  This is necessary when the screenshot is not called in
     * the traditional manner.
     *.
     * @param name Unique name to further identify the screenshot. Must match [a-zA-Z0-9_-]+.
     * @param screenshotDirectory Output directory, which will be resolved with the [screenshotDir]
     * @return the image file that was created
     */
    fun screenshot(name: String, screenshotDirectory: File): File {
        validateScreenshotTag(name)
        val screenshotFile = screenshotDirectory.resolve(name + EXTENSION)
        try {
            createDir(screenshotDirectory)
            takeScreenshot(screenshotFile)
            logger.d(TAG, "Captured screenshot '$name'.")
            return screenshotFile
        } catch (e: Exception) {
            throw RuntimeException("Unable to capture screenshot.", e)
        }
    }

    private fun validateScreenshotTag(tag: String) {
        if (!TAG_VALIDATION.matcher(tag).matches()) {
            throw IllegalArgumentException("Tag must match " + TAG_VALIDATION.pattern() + ". : $tag")
        }
    }

    @Throws(IOException::class)
    private fun takeScreenshot(file: File) {
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).takeScreenshot(file)
    }

    @Throws(IllegalAccessException::class)
    private fun obtainScreenshotDirectory(
        context: Context, testClassName: String,
        testMethodName: String
    ): File {
        return filesDirectory(
            context,
            testClassName,
            testMethodName
        )
    }

    /**
     * Clears spoon-screenshots folder.
     */
    @Throws(IOException::class)
    @Suppress("UNUSED")
    fun clearScreenshotsFolder(): Boolean {
        val directory: File = getDeviceDirectory(InstrumentationRegistry.getTargetContext())
        return if (directory.exists()) {
            directory.deleteRecursively()
        } else {
            true
        }
    }

    @SuppressLint("WorldReadableFiles")
    @Throws(IllegalAccessException::class)
    private fun filesDirectory(
        context: Context, testClassName: String,
        testMethodName: String
    ): File {
        val directory: File = getDeviceDirectory(context)

        synchronized(LOCK) {
            if (!clearedOutputDirectories.contains(directory)) {
                deletePath(directory, inclusive = false)
                clearedOutputDirectories.add(directory)
            }
        }

        val dirClass = directory.resolve(testClassName)
        val dirMethod = dirClass.resolve(testMethodName)
        createDir(dirMethod)

        return dirMethod
    }

    private fun getDeviceDirectory(context: Context): File {
        return if (Build.VERSION.SDK_INT >= 21) {
            // Use external storage.
            getExternalStorageDirectory().resolve(screenshotDir)
        } else {
            // Use internal storage.
            context.getDir(screenshotDir.canonicalPath, MODE_WORLD_READABLE)
        }
    }

    /**
     * Returns the test class element by looking at the method InstrumentationTestCase invokes.
     */
    private fun findTestClassTraceElement(trace: Array<StackTraceElement>): StackTraceElement {
        for (i in trace.indices.reversed()) {
            val element = trace[i]

            if (TEST_CASE_CLASS_JUNIT_3 == element.className //
                && TEST_CASE_METHOD_JUNIT_3 == element.methodName
            ) {
                return extractStackElement(trace, i)
            }

            if (TEST_CASE_CLASS_JUNIT_4 == element.className //
                && TEST_CASE_METHOD_JUNIT_4 == element.methodName
            ) {
                return extractStackElement(trace, i)
            }

            if (TEST_CASE_CLASS_CUCUMBER_JVM == element.className //
                && TEST_CASE_METHOD_CUCUMBER_JVM == element.methodName
            ) {
                return extractStackElement(trace, i)
            }
        }

        throw IllegalArgumentException("Could not find test class! Trace: ${trace.map { it.toString() }}")
    }

    private fun extractStackElement(trace: Array<StackTraceElement>, i: Int): StackTraceElement {
        //Stacktrace length changed in M
        val testClassTraceIndex = if (Build.VERSION.SDK_INT >= 23) i - 2 else i - 3
        return trace[testClassTraceIndex]
    }

    @Throws(IllegalAccessException::class)
    private fun createDir(dir: File) {
        val parent = dir.parentFile

        if (!parent.exists()) {
            createDir(parent)
        }

        if (!dir.exists() && !dir.mkdirs()) {
            throw IllegalAccessException("Unable to create output dir: " + dir.absolutePath)
        }

        chmodPlusRWX(dir)
    }

    private fun deletePath(path: File, inclusive: Boolean) {
        if (path.isDirectory) {
            val children = path.listFiles()

            if (children != null) {
                for (child in children) {
                    deletePath(child, true)
                }
            }
        }

        if (inclusive) {
            path.delete()
        }
    }
}

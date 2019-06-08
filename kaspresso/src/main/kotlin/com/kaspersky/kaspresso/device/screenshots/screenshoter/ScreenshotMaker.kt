package com.kaspersky.kaspresso.device.screenshots.screenshoter

import android.content.Context
import android.os.Build
import android.os.Environment
import com.kaspersky.kaspresso.device.screenshots.screenshoter.external.Chmod
import com.kaspersky.kaspresso.logger.UiTestLogger
import java.io.File
import java.util.*

internal abstract class ScreenshotMaker(
    protected val screenshotDir: File,
    protected val logger: UiTestLogger
) {

    companion object {
        private const val SPOON_SCREENSHOTS = "spoon-screenshots"
        private const val SPOON_FILES = "spoon-files"
        private const val NAME_SEPARATOR = "_"
        private const val TEST_CASE_CLASS_JUNIT_3 = "android.test.InstrumentationTestCase"
        private const val TEST_CASE_METHOD_JUNIT_3 = "runMethod"
        private const val TEST_CASE_CLASS_JUNIT_4 = "org.junit.runners.model.FrameworkMethod$1"
        private const val TEST_CASE_METHOD_JUNIT_4 = "runReflectiveCall"
        private const val TEST_CASE_CLASS_CUCUMBER_JVM = "cucumber.runtime.model.CucumberFeature"
        private const val TEST_CASE_METHOD_CUCUMBER_JVM = "run"

        private val LOCK = Any()
        private const val EXTENSION = ".png"
    }

    /**
     * Holds a set of directories that have been cleared for this test.
     */
    private val clearedOutputDirectories = HashSet<File>()


    fun obtainScreenshotFile(context: Context, tag: String): File {
        val testClass = findTestClassTraceElement(Thread.currentThread().stackTrace)
        val className = testClass.className.replace("[^A-Za-z0-9._-]".toRegex(), NAME_SEPARATOR)
        val methodName = testClass.methodName

        val screenshotDirectory = filesDirectory(context, className, methodName)

        val screenshotName = System.currentTimeMillis().toString() + NAME_SEPARATOR + tag + EXTENSION
        return screenshotDirectory.resolve(screenshotName)
    }

    fun findTestClassTraceElement(trace: Array<StackTraceElement>): StackTraceElement {
        return trace.reversed().withIndex()
            .find { (_, element) -> element.isJunit3() || element.isJunit4() || element.isCucumber() }
            ?.let { (i, _) -> extractStackElement(trace, i) }
            ?: throw IllegalArgumentException("Could not find test class! Trace: ${trace.map { it.toString() }}")
    }

    private fun getDeviceDirectory(context: Context): File {
        return if (Build.VERSION.SDK_INT >= 21) {
            // Use external storage.
            Environment.getExternalStorageDirectory().resolve(screenshotDir)
        } else {
            // Use internal storage.
            context.getDir(screenshotDir.canonicalPath, Context.MODE_WORLD_READABLE)
        }
    }

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

    private fun createDir(dir: File) {
        val parent = dir.parentFile

        if (!parent.exists()) {
            createDir(parent)
        }

        if (!dir.exists() && !dir.mkdirs()) {
            throw IllegalAccessException("Unable to create output dir: " + dir.absolutePath)
        }

        Chmod.chmodPlusRWX(dir)
    }

    private fun deletePath(path: File, inclusive: Boolean) {
        if (path.isDirectory) {
            path.listFiles()?.run {
                forEach { child -> deletePath(child, true) }
            }
        }

        if (inclusive) path.delete()
    }

    private fun StackTraceElement.isJunit3(): Boolean {
        return TEST_CASE_CLASS_JUNIT_3 == className && TEST_CASE_METHOD_JUNIT_3 == methodName
    }

    private fun StackTraceElement.isJunit4(): Boolean {
        return TEST_CASE_CLASS_JUNIT_4 == className && TEST_CASE_METHOD_JUNIT_4 == methodName
    }

    private fun StackTraceElement.isCucumber(): Boolean {
        return TEST_CASE_CLASS_CUCUMBER_JVM == className && TEST_CASE_METHOD_CUCUMBER_JVM == methodName
    }

    private fun extractStackElement(trace: Array<StackTraceElement>, i: Int): StackTraceElement {
        //Stacktrace length changed in M
        val testClassTraceIndex = if (Build.VERSION.SDK_INT >= 23) i - 2 else i - 3
        return trace[testClassTraceIndex]
    }
}
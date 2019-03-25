package com.kaspersky.kaspresso.extensions.spoonext

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_WORLD_READABLE
import android.os.Build
import android.os.Environment.getExternalStorageDirectory
import android.support.test.InstrumentationRegistry
import android.support.test.uiautomator.UiDevice
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.extensions.spoonext.Chmod.Companion.chmodPlusR
import com.kaspersky.kaspresso.extensions.spoonext.Chmod.Companion.chmodPlusRWX
import java.io.*
import java.util.*
import java.util.regex.Pattern

/**
 * Utility class for capturing spoon-compatible screenshots by uiautomator.
 */
object UiAutomatorSpoon {

    private const val SPOON_SCREENSHOTS = "spoon-screenshots"
    private const val SPOON_FILES = "spoon-files"

    private const val NAME_SEPARATOR = "_"

    private const val TEST_CASE_CLASS_JUNIT_3 = "android.test.InstrumentationTestCase"
    private const val TEST_CASE_METHOD_JUNIT_3 = "runMethod"

    private const val TEST_CASE_CLASS_JUNIT_4 = "org.junit.runners.model.FrameworkMethod$1"
    private const val TEST_CASE_METHOD_JUNIT_4 = "runReflectiveCall"

    private const val TEST_CASE_CLASS_CUCUMBER_JVM = "cucumber.runtime.model.CucumberFeature"
    private const val TEST_CASE_METHOD_CUCUMBER_JVM = "run"

    private const val EXTENSION = ".png"
    private const val TAG = "UiAutomatorSpoon"

    private val LOCK = Any()
    private val TAG_VALIDATION = Pattern.compile("[a-zA-Z0-9_-]+")

    /**
     * Holds a set of directories that have been cleared for this test.
     */
    private val clearedOutputDirectories = HashSet<String>()

    private val logger by lazy { Configurator.logger }

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
        if (!TAG_VALIDATION.matcher(tag).matches()) {
            throw IllegalArgumentException("Tag must match " + TAG_VALIDATION.pattern() + ".")
        }

        try {
            val screenshotDirectory =
                obtainScreenshotDirectory(
                    InstrumentationRegistry.getTargetContext().applicationContext,
                    testClassName,
                    testMethodName
                )

            val screenshotName = System.currentTimeMillis().toString() + NAME_SEPARATOR + tag + EXTENSION
            val screenshotFile = File(screenshotDirectory, screenshotName)

            takeScreenshot(screenshotFile)

            logger.d(TAG, "Captured screenshot '$tag'.")
            return screenshotFile
        } catch (e: Exception) {
            throw RuntimeException("Unable to capture screenshot.", e)
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
            SPOON_SCREENSHOTS,
            testClassName,
            testMethodName
        )
    }

    /**
     * Alternative to [.save]
     * @param context Context used to access files.
     * @param path Path to the file you want to save.
     * @return the copy that was created.
     */
    @Suppress("UNUSED")
    fun save(context: Context, path: String): File {
        return save(context, File(path))
    }

    /**
     * Save a file from this test run. The file will be saved under the current class & method.
     * The file will be copied to, so make sure all the data you want have been
     * written to the file before calling save.
     *
     * @param context Context used to access files.
     * @param file The file to save.
     * @return the copy that was created.
     */
    @Suppress("WEAKER_ACCESS", "UNUSED")
    fun save(context: Context, file: File): File {
        val testClass =
            findTestClassTraceElement(Thread.currentThread().stackTrace)
        val className = testClass.className.replace("[^A-Za-z0-9._-]".toRegex(), "_")
        val methodName = testClass.methodName

        return save(context, className, methodName, file)
    }

    /**
     * Clears spoon-screenshots folder.
     */
    @Throws(IOException::class)
    @Suppress("UNUSED")
    fun clearScreenshotsFolder(): Boolean =
        clearFolder(SPOON_SCREENSHOTS)

    @SuppressLint("WorldReadableFiles")
    @Throws(IOException::class)
    private fun clearFolder(directoryType: String): Boolean {
        val directory: File = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Use external storage.
            File(getExternalStorageDirectory(), "app_$directoryType")
        } else {
            // Use internal storage.
            InstrumentationRegistry.getTargetContext().getDir(directoryType, MODE_WORLD_READABLE)
        }
        return if (directory.exists()) {
            directory.deleteRecursively()
        } else {
            true
        }
    }

    private fun save(context: Context, className: String, methodName: String, file: File): File {
        var filesDirectory: File? = null

        try {
            filesDirectory = filesDirectory(
                context,
                SPOON_FILES,
                className,
                methodName
            )

            if (!file.exists()) {
                throw RuntimeException("Can't find any file at: $file")
            }

            val target = File(filesDirectory, file.name)
            copy(file, target)

            logger.d(TAG, "Saved $file")
            return target
        } catch (e: IllegalAccessException) {
            throw RuntimeException("Unable to save file: $file")
        } catch (e: IOException) {
            throw RuntimeException("Couldn't copy file $file to $filesDirectory")
        }
    }

    @Throws(IOException::class)
    private fun copy(source: File, target: File) {
        logger.d(TAG, "Will copy $source to $target")

        target.createNewFile()
        chmodPlusR(target)

        val `is` = BufferedInputStream(FileInputStream(source))
        val os = BufferedOutputStream(FileOutputStream(target))
        val buffer = ByteArray(4096)

        while (`is`.read(buffer) > 0) {
            os.write(buffer)
        }

        `is`.close()
        os.close()
    }

    @SuppressLint("WorldReadableFiles")
    @Throws(IllegalAccessException::class)
    private fun filesDirectory(
        context: Context, directoryType: String, testClassName: String,
        testMethodName: String
    ): File {
        val directory: File

        if (Build.VERSION.SDK_INT >= 21) {
            // Use external storage.
            directory = File(getExternalStorageDirectory(), "app_$directoryType")
        } else {
            // Use internal storage.
            directory = context.getDir(directoryType, MODE_WORLD_READABLE)
        }

        synchronized(LOCK) {
            if (!clearedOutputDirectories.contains(directoryType)) {
                deletePath(directory, false)
                clearedOutputDirectories.add(directoryType)
            }
        }

        val dirClass = File(directory, testClassName)
        val dirMethod = File(dirClass, testMethodName)
        createDir(dirMethod)

        return dirMethod
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

        throw IllegalArgumentException("Could not find test class!")
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
}// No instances.
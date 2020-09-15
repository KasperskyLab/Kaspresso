package com.kaspersky.kaspresso.device.screenshots.screenshotfiles

import android.os.Build

private const val TEST_CASE_CLASS_JUNIT_3 = "android.test.InstrumentationTestCase"
private const val TEST_CASE_METHOD_JUNIT_3 = "runMethod"
private const val TEST_CASE_CLASS_JUNIT_4 = "org.junit.runners.model.FrameworkMethod$1"
private const val TEST_CASE_METHOD_JUNIT_4 = "runReflectiveCall"
private const val TEST_CASE_CLASS_CUCUMBER_JVM = "cucumber.runtime.model.CucumberFeature"
private const val TEST_CASE_METHOD_CUCUMBER_JVM = "run"

internal fun Array<StackTraceElement>.findTestMethod(): TestMethod {
    return findTestClassTraceElement()
        .let { TestMethod(it.className, it.methodName) }
}

private fun Array<StackTraceElement>.findTestClassTraceElement(): StackTraceElement {
    return this.withIndex().reversed()
        .find { (_, element) -> element.isJunit3() || element.isJunit4() || element.isCucumber() }
        ?.let { (i, _) -> extractStackElement(i) }
        ?: throw IllegalArgumentException("Could not find test class! Trace: ${this.map { it.toString() }}")
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

@Suppress("MagicNumber")
private fun Array<StackTraceElement>.extractStackElement(i: Int): StackTraceElement {
    // Stacktrace length changed in M
    val testClassTraceIndex = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) i - 2 else i - 3
    return this[testClassTraceIndex]
}

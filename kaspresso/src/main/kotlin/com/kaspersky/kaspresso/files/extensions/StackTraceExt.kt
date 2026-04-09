package com.kaspersky.kaspresso.files.extensions

import android.os.Build
import com.kaspersky.kaspresso.files.models.TestMethod

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

private const val TEST_CASE_CLASS_JUNIT_3 = "android.test.InstrumentationTestCase"
private const val TEST_CASE_METHOD_JUNIT_3 = "runMethod"
private const val TEST_CASE_CLASS_JUNIT_4 = "org.junit.runners.model.FrameworkMethod$1"
private const val TEST_CASE_METHOD_JUNIT_4 = "runReflectiveCall"
private const val TEST_CASE_CLASS_CUCUMBER_JVM = "cucumber.runtime.model.CucumberFeature"
private const val TEST_CASE_METHOD_CUCUMBER_JVM = "run"

internal fun Array<StackTraceElement>.findTestMethod(): TestMethod? {
    val testTraceElement = findTestClassTraceElement()
    return testTraceElement?.let { TestMethod(it.className, it.methodName) }
}

private fun Array<StackTraceElement>.findTestClassTraceElement(): StackTraceElement? {
    return this.withIndex().reversed()
        .find { (_, element) -> element.isJunit3() || element.isJunit4() || element.isCucumber() }
        ?.let { (i, _) -> extractStackElement(i) }
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

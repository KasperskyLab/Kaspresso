package com.kaspersky.kaspresso.testcases.api.testcase

import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.test.espresso.ViewAssertion
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext
import io.github.kakaocup.kakao.common.assertions.BaseAssertions
import androidx.core.graphics.createBitmap

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

abstract class VisualTestCase(
    kaspressoBuilder: Kaspresso.Builder = Kaspresso.Builder.simple(),
) : TestCase(kaspressoBuilder) {

    open fun runScreenshotTest(
        before: (BaseTestContext.() -> Unit)? = null,
        after: (BaseTestContext.() -> Unit)? = null,
        test: TestContext<Unit>.() -> Unit,
    ) = before {
        kaspresso.visualTestWatcher.prepare()
        before?.invoke(this)
    }.after {
        kaspresso.visualTestWatcher.cleanUp()
        after?.invoke(this)
    }.run(test)

    open fun assertScreenshot(tag: String, isFullWindow: Boolean = false) {
        device.screenshots.assert(tag, isFullWindow)
    }

    fun BaseAssertions.assertScreenshot(tag: String) {
        assert { createScreenshotAssertion(tag) }
    }

    private fun createScreenshotAssertion(tag: String): ViewAssertion {
        return ViewAssertion { view, noViewFoundException ->
            if (view == null) {
                throw noViewFoundException ?: AssertionError("Target view is null")
            }

            check(view.width > 0 && view.height > 0) {
                "Target view should have non-zero size, but was ${view.width}x${view.height}"
            }

            val bitmap = createBitmap(view.width, view.height)
            val canvas = Canvas(bitmap)
            view.draw(canvas)
            device.screenshots.assert(tag, bitmap)
        }
    }
}

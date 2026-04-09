package com.kaspersky.kaspresso.device.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Instrumentation
import android.os.Looper
import androidx.test.internal.runner.junit4.statement.UiThreadStatement
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import com.kaspersky.kaspresso.logger.UiTestLogger
import org.hamcrest.CoreMatchers
import org.junit.Assert

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
 * The implementation of the [Activities] interface.
 */
class ActivitiesImpl(
    private val logger: UiTestLogger,
    private val instrumentation: Instrumentation,
) : Activities {

    /**
     * Checks if this is a main thread.
     */
    private val isMainThread: Boolean
        get() = Looper.myLooper() == Looper.getMainLooper()

    /**
     * Finds and returns resumed activity if it exists, otherwise logs error.
     *
     * @return nullable resumed activity.
     */
    override fun getResumed(): Activity? {
        var resumedActivity: Activity? = null

        val findResumedActivity = {
            val resumedActivities = ActivityLifecycleMonitorRegistry.getInstance()
                .getActivitiesInStage(Stage.RESUMED)

            if (resumedActivities.iterator().hasNext()) {
                resumedActivity = resumedActivities.iterator().next()
            }
        }

        if (isMainThread) {
            findResumedActivity()
        } else {
            instrumentation.runOnMainSync(findResumedActivity)
        }

        resumedActivity ?: logger.e("No resumed activity found")

        return resumedActivity
    }

    /**
     * Checks if passed activity is resumed.
     */
    @SuppressLint("RestrictedApi")
    override fun isCurrent(clazz: Class<out Activity>) {
        UiThreadStatement.runOnUiThread {
            Assert.assertThat(
                getResumed(),
                CoreMatchers.instanceOf(clazz)
            )
        }
    }

    /**
     * A form of [isCurrent] method for simplified usage.
     */
    inline fun <reified T : Activity> assertCurrentActivity() = isCurrent(T::class.java)
}

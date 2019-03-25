package com.kaspersky.uitest_framework.device

import android.app.Activity
import android.os.Looper
import android.support.test.InstrumentationRegistry
import android.support.test.internal.runner.junit4.statement.UiThreadStatement
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import android.support.test.runner.lifecycle.Stage
import com.kaspersky.uitest_framework.configurator.Configurator
import com.kaspersky.uitest_framework.logger.UiTestLogger
import org.hamcrest.CoreMatchers
import org.junit.Assert

class ActivitiesManager {

    private val logger: UiTestLogger = Configurator.logger

    private val isMainThread: Boolean
        get() = Looper.myLooper() == Looper.getMainLooper()

    inline fun <reified T : Activity> assertCurrentActivity() {
        UiThreadStatement.runOnUiThread {
            Assert.assertThat(
                getResumedActivity(),
                CoreMatchers.instanceOf(T::class.java)
            )
        }
    }

    fun getResumedActivity(): Activity? {
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
            InstrumentationRegistry.getInstrumentation().runOnMainSync(findResumedActivity)
        }

        resumedActivity ?: logger.e("No resumed activity found")

        return resumedActivity
    }
}
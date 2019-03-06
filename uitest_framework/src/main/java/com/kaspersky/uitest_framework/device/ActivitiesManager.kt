package com.kaspersky.uitest_framework.device

import android.app.Activity
import android.os.Looper
import android.support.test.InstrumentationRegistry
import android.support.test.internal.runner.junit4.statement.UiThreadStatement
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import android.support.test.runner.lifecycle.Stage
import org.hamcrest.CoreMatchers
import org.junit.Assert

/**
 * Created by egor.kurnikov on 06.03.2019
 */

class ActivitiesManager {

    val isMainThread: Boolean
        get() = Looper.myLooper() == Looper.getMainLooper()

    inline fun <reified T : Activity> assertCurrentActivity() {

        UiThreadStatement.runOnUiThread {
            Assert.assertThat(
                    getResumedActivity(),
                    CoreMatchers.instanceOf(T::class.java)
            )
        }
    }

    fun getResumedActivity(): Activity {

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

        return resumedActivity ?: throw IllegalStateException("Resumed activity not found")
    }
}
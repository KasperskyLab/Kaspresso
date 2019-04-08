package com.kaspersky.kaspresso.device.activities

import android.app.Activity
import android.os.Looper
import android.support.test.InstrumentationRegistry
import android.support.test.internal.runner.junit4.statement.UiThreadStatement
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import android.support.test.runner.lifecycle.Stage
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.logger.UiTestLogger
import org.hamcrest.CoreMatchers
import org.junit.Assert

/**
 * Default implementation of Activities interface.
 */
class ActivitiesImpl : Activities {

    private val logger: UiTestLogger = Configurator.logger

    /**
     * Checks if this is a main thread.
     */
    private val isMainThread: Boolean
        get() = Looper.myLooper() == Looper.getMainLooper()

    /**
     * Finds and returns resumed activity if it exists, otherwise logs error.
     *
     * @return nullable resumed activity
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
            InstrumentationRegistry.getInstrumentation().runOnMainSync(findResumedActivity)
        }

        resumedActivity ?: logger.e("No resumed activity found")

        return resumedActivity
    }

    /**
     * Checks if passed activity is resumed.
     */
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
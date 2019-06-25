package com.kaspersky.kaspresso.rule

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.lifecycle.ActivityLifecycleCallback
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import android.support.test.runner.lifecycle.Stage
import java.util.*
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 *  Test rule to switch locales.
 */
class LocaleRule internal constructor(
    private val locales: Set<Locale>
) : TestRule {

    private var deviceLocale: Locale? = null
    private var currentLocale: Locale? = null

    val locale: Locale
        get() = currentLocale!!

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {

            override fun evaluate() {
                val callback = ActivityLifecycleCallback { activity, stage ->
                    if (stage == Stage.CREATED) {
                        applyCurrentLocaleToContext(activity)
                    }
                }

                ActivityLifecycleMonitorRegistry.getInstance().addLifecycleCallback(callback)

                try {
                    deviceLocale = Locale.getDefault()

                    for (locale in locales) {
                        currentLocale = locale
                        applyCurrentLocaleToContext(InstrumentationRegistry.getTargetContext())
                        base.evaluate()
                    }
                } finally {
                    if (deviceLocale != null) {
                        currentLocale = deviceLocale!!
                        applyCurrentLocaleToContext(InstrumentationRegistry.getTargetContext())
                    }

                    ActivityLifecycleMonitorRegistry.getInstance().removeLifecycleCallback(callback)
                }
            }
        }
    }

    private fun applyCurrentLocaleToContext(context: Context) {
        val resources = context.resources
        Locale.setDefault(currentLocale)
        val configuration = resources.configuration

        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.JELLY_BEAN) {
            configuration.setLocale(currentLocale)
        } else {
            configuration.locale = currentLocale
        }

        resources.updateConfiguration(configuration, resources.displayMetrics)
    }
}

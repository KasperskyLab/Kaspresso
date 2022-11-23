package com.kaspersky.components.alluresupport.runner

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kaspersky.components.kautomator.common.Environment
import com.kaspersky.components.kautomator.common.environment
import org.junit.runner.Description
import org.junit.runner.Runner
import org.junit.runner.manipulation.Filter
import org.junit.runner.manipulation.Filterable
import org.junit.runner.manipulation.Sortable
import org.junit.runner.manipulation.Sorter
import org.junit.runner.notification.RunNotifier

class KaspressoAndroidJUnit4(clazz: Class<*>) : Runner(), Filterable, Sortable {
    private val delegate = AndroidJUnit4(clazz)

    override fun run(notifier: RunNotifier?) {
        if (environment is Environment.AndroidRuntime) {
            notifier?.addListener(KaspressoRunListener())
        }
        delegate.run(notifier)
    }

    override fun getDescription(): Description = delegate.description

    override fun filter(filter: Filter?): Unit = delegate.filter(filter)

    override fun sort(sorter: Sorter?): Unit = delegate.sort(sorter)
}

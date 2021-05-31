package com.kaspersky.kaspressonitrogen.interceptors.watcher.testcase.impl.defaults

import com.kaspersky.kaspresso.interceptors.watcher.testcase.NitrogenTestRunWatcherInterceptor
import com.kaspersky.kaspressonitrogen.testcases.core.testcontext.NitrogenBaseTestContext
import com.kaspersky.kaspresso.testcases.models.info.TestInfo

class NitrogenDefaultTestRunWatcherInterceptor: NitrogenTestRunWatcherInterceptor {

    private lateinit var context: NitrogenBaseTestContext

    /**
     * Holds the action which will be executed before the test.
     * The action has access to _root_ide_package_.com.kaspersky.kaspressonitrogen.testcases.core.testcontext.NitrogenBaseTestContext.
     */
    private var beforeEachTestAction: (NitrogenBaseTestContext.() -> Unit)? = null

    /**
     * Holds the action which will be executed after the test.
     * The action has access to _root_ide_package_.com.kaspersky.kaspressonitrogen.testcases.core.testcontext.NitrogenBaseTestContext.
     */
    private var afterEachTestAction: (NitrogenBaseTestContext.() -> Unit)? = null

    override fun setBaseTestContext(context: NitrogenBaseTestContext) {
        this.context = context
    }

    override fun onBeforeSectionStarted(testInfo: TestInfo) {
        beforeEachTestAction?.invoke(context)
    }

    override fun onAfterSectionFinishedSuccess(testInfo: TestInfo) {
        afterEachTestAction?.invoke(context)
    }

    override fun onAfterSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        afterEachTestAction?.invoke(context)
    }

    /**
     * Set the action which will be executed before the test.
     * The action has access to BaseTestContext.
     * If you set @param override in false then the final beforeAction will be
     *     beforeAction of the parent TestCase plus current @param action.
     *     Otherwise final beforeAction will be only @param action.
     */
    fun beforeEachTest(override: Boolean = false, action: NitrogenBaseTestContext.() -> Unit) {
        if (override) {
            beforeEachTestAction = action
        } else {
            val oldBeforeEachTestAction = beforeEachTestAction
            beforeEachTestAction = {
                oldBeforeEachTestAction?.invoke(this)
                action.invoke(this)
            }
        }
    }

    /**
     * Set the action which will be executed after the test.
     * The action has access to BaseTestContext.
     * If you set @param override in false then the final beforeAction will be
     *     beforeAction of the parent TestCase plus current @param action.
     *     Otherwise final beforeAction will be only @param action.
     */
    fun afterEachTest(override: Boolean = false, action: NitrogenBaseTestContext.() -> Unit) {
        if (override) {
            afterEachTestAction = action
        } else {
            val oldAfterEachTestAction = afterEachTestAction
            afterEachTestAction = {
                oldAfterEachTestAction?.invoke(this)
                action.invoke(this)
            }
        }
    }
}


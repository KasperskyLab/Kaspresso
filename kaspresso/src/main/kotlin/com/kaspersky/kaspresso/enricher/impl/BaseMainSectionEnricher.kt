package com.kaspersky.kaspresso.enricher.impl

import com.kaspersky.kaspresso.enricher.MainSectionEnricher
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext
import com.kaspersky.kaspresso.testcases.models.info.TestInfo

/**
 * Base implementation of [com.kaspersky.kaspresso.enricher.MainSectionEnricher] interface for supporting DSL-style
 * of enriching main section.
 *
 * In [enrichMainSection] method you should add before/after actions into your enricher, like this:
 *
 * <code>
 *     class MyEnricher : BaseMainSectionEnricher<Data>() {
 *
 *         override fun enrichMainSection() {
 *             beforeMainSectionRun { testInfo ->
 *                 // here, this == TestContext<Data>, so we can use all properties and functions of TestContext
 *                 step("My before main section run action | test info: $testInfo") {
 *                     // do something
 *                 }
 *             }
 *
 *             afterMainSectionRun { testInfo ->
 *                 // here, this == TestContext<Data>, so we can use all properties and functions of TestContext
 *             }
 *         }
 *
 *     }
 * </code>
 *
 * @param Data - The same data type as in your [com.kaspersky.kaspresso.testcases.api.testcase.BaseTestCase].
 */
abstract class BaseMainSectionEnricher<Data> : MainSectionEnricher<Data> {

    private var beforeMainSectionRunAction: (TestContext<Data>.(TestInfo) -> Unit)? = null
    private var afterMainSectionRunAction: (TestContext<Data>.(TestInfo) -> Unit)? = null

    /**
     * In this method you will add [beforeMainSectionRun] and [afterMainSectionRun] actions for your enricher.
     */
    abstract fun enrichMainSection()

    final override fun beforeMainSectionRun(testInfo: TestInfo, testContext: TestContext<Data>) {
        beforeMainSectionRunAction?.invoke(testContext, testInfo)
    }

    final override fun afterMainSectionRun(testInfo: TestInfo, testContext: TestContext<Data>) {
        afterMainSectionRunAction?.invoke(testContext, testInfo)
    }

    /**
     * Use this method to add actions before main section 'run' block.
     */
    fun beforeMainSectionRun(action: TestContext<Data>.(TestInfo) -> Unit) {
        this.beforeMainSectionRunAction = action
    }

    /**
     * Use this method to add actions after main section 'run' block.
     */
    fun afterMainSectionRun(action: TestContext<Data>.(TestInfo) -> Unit) {
        this.afterMainSectionRunAction = action
    }
}
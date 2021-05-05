package com.kaspersky.kaspressonitrogen.enricher

import com.kaspersky.kaspressonitrogen.testcases.core.testcontext.NitrogenTestContext
import com.kaspersky.kaspresso.testcases.models.info.TestInfo

/**
 * Special object for enriching 'run'-block functionality.
 * With this object you can add some additional test steps for each TestCase, that has this enricher, like this:
 *
 * <code>
 * class MyMainSectionEnricher : MainSectionEnricher<TestCaseData> {
 *
 *     override fun TestContext<TestCaseData>.beforeMainSectionRun(testInfo: TestInfo) {
 *         step("New step before 'run' block") {
 *             step("Nested step inside") {
 *                 // do additional stuff
 *             }
 *         }
 *     }
 * }
 * </code>
 *
 * @param Data - The same data type as in your [com.kaspersky.kaspresso.testcases.api.testcase.NitrogenBaseTestCase].
 */
interface NitrogenMainSectionEnricher<Data> {
    /**
     * This method will be invoked right before execution of "run" block in your test case.
     *
     * @param testInfo - test information, such as test identifier
     */
    fun NitrogenTestContext<Data>.beforeMainSectionRun(testInfo: TestInfo) = Unit

    /**
     * This method will be invoked right after execution of "run" block in your test case.
     *
     * @param testInfo - test information, such as test identifier
     */
    fun NitrogenTestContext<Data>.afterMainSectionRun(testInfo: TestInfo) = Unit
}

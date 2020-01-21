package com.kaspersky.kaspresso.testcases.api.testcase

import com.kaspersky.kaspresso.kaspresso.Kaspresso

/**
 * The base class for all test cases. Extend this class with a single base project-wide inheritor of [TestCase] as a
 * parent for all actual project-wide test cases. Nesting test cases are not permitted because they may produce an
 * exception caused by re-initialization of the [Kaspresso], use
 * [com.kaspersky.kaspresso.testcases.api.scenario.Scenario] instead.
 */
abstract class TestCase(
    kaspressoBuilder: Kaspresso.Builder = Kaspresso.Builder.advanced()
) : BaseTestCase<Unit, Unit>(
    kaspressoBuilder = kaspressoBuilder,
    dataProducer = { action -> action?.invoke(Unit) }
)
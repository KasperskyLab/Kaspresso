package com.kaspersky.kaspresso.testcases.api.testcaserule

import com.kaspersky.kaspresso.kaspresso.Kaspresso

/**
 * The base class for all test cases. Extend this class with a single base project-wide inheritor of
 * [com.kaspersky.kaspresso.testcases.api.testcase.TestCase] as a parent for all actual project-wide test cases.
 * Nesting test cases are not permitted because they may produce an exception caused by re-initialization of the
 * [Kaspresso], use [com.kaspersky.kaspresso.testcases.api.scenario.Scenario] instead.
 */
class TestCaseRule(
    testClassName: String,
    kaspressoBuilder: Kaspresso.Builder = Kaspresso.Builder.advanced()
) : BaseTestCaseRule<Unit, Unit>(
    testClassName = testClassName,
    kaspressoBuilder = kaspressoBuilder,
    dataProducer = { action -> action?.invoke(Unit) }
)
package com.kaspersky.kaspresso.testcases.api.testcaserule

import com.kaspersky.kaspresso.testcases.core.sections.AfterTestSection
import com.kaspersky.kaspresso.testcases.core.sections.InitSection
import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext
import leakcanary.LeakAssertions
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class DetectMemoryLeaksRule(
    testClassName: String
) : TestRule {

    val kaspressoRule = TestCaseRule(testClassName)

    override fun apply(base: Statement, description: Description): Statement {
        return kaspressoRule.apply(base, description)
    }

    fun before(actions: BaseTestContext.() -> Unit) = After(kaspressoRule.before {
        actions(this)
    })

    class After(
        private val after: AfterTestSection<Unit, Unit>
    ) {
        fun after(actions: BaseTestContext.() -> Unit) = Init(after.after {
            LeakAssertions.assertNoLeaks()
            actions(this)
        })
    }

    class Init(
        private val init: InitSection<Unit, Unit>
    ) {
        fun run(steps: TestContext<Unit>.() -> Unit) = init.run {
            steps(this)
        }
    }
}

package com.kaspersky.kaspresso.testcases

/**
 * An implementation of [ScenarioRunner] for [TestCase]'s usage.
 */
class MainTestSection(private val builder: TestBody.Builder) : ScenarioRunner {

    /**
     * Runs [beforeTestActions], [TestCase]'s [steps] and then runs [afterTestActions]. [afterTestActions] are invoked
     * even if [beforeTestActions] or [TestCase]'s [steps] fail.
     *
     * @param steps steps to run.
     */
    override fun runSteps(steps: Scenario.() -> Unit) {
        builder
            .mainSection(steps)
            .build()
            .run()
    }
}
package com.kaspersky.uitest_framework.testcase

import com.kaspersky.uitest_framework.configurator.Configurator

/**
 *  Base class for all test cases.
 *  Extend the class with a project-wide TestCase and avoid direct inheritance from this one.
 *  Nesting TestCases is not permitted and may produce an Exception
 *  caused by re-initialization of the configurator, use [Scenario] instead
 */
abstract class TestCase(
    configBuilder: Configurator.Builder = Configurator.Builder().default()
) : Scenario() {

    init {
        configBuilder.commit()
    }
}

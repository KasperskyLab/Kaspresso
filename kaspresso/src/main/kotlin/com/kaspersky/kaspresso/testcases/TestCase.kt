package com.kaspersky.kaspresso.testcases

import com.kaspersky.kaspresso.configurator.Configurator

/**
 *  A base class for all test cases. Extend this class with a single base project-wide inheritor of [TestCase] as a
 *  parent for all actual project-wide test cases. Nesting test cases are not permitted because they may produce an
 *  exception caused by re-initialization of the [Configurator], use [Scenario] instead.
 */
abstract class TestCase(configBuilder: Configurator.Builder = Configurator.Builder.default()) :
    BaseTestCase<Unit, Unit>(configBuilder)
package com.kaspersky.kaspresso.testcases

import com.kaspersky.kaspresso.configurator.Configurator

class TestCaseRule(
    context: Any,
    configBuilder: Configurator.Builder = Configurator.Builder.default()
) : BaseTestCaseRule<Unit, Unit>(context, configBuilder)
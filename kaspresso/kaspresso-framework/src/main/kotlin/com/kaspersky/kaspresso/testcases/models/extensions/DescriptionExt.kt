package com.kaspersky.kaspresso.testcases.models.extensions


import com.kaspersky.kaspresso.testcases.models.TestIdentifier
import org.junit.runner.Description


fun Description.toTestIdentifier() = TestIdentifier(className, methodName)
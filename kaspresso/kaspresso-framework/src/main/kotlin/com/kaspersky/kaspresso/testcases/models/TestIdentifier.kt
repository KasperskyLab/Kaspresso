package com.kaspersky.kaspresso.testcases.models

/**
 * Unique test identifier which consists of two parameters: class name with specifying package name + test method name.
 */
data class TestIdentifier(
    val className: String,
    val methodName: String
) {

    override fun toString(): String {
        return "className: $className, methodName: $methodName"
    }
}
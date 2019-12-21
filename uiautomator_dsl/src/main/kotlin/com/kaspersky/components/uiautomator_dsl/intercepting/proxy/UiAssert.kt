package com.kaspersky.components.uiautomator_dsl.intercepting.proxy

import androidx.test.uiautomator.UiObject2

interface UiAssert {

    fun getDescription(): String

    fun check(interaction: UiObject2)

}
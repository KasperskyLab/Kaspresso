package com.kaspersky.components.uiautomator_dsl.intercepting.proxy

import androidx.test.uiautomator.UiObject2

interface UiAction {

    fun getDescription(): String

    fun perform(interaction: UiObject2)

}
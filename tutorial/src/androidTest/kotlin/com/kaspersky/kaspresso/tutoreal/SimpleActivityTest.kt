package com.kaspersky.kaspresso.tutoreal

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutoreal.screen.MainSceen
import com.kaspersky.kaspresso.tutoreal.screen.SimpleActivityScreen
import com.kaspersky.kaspresso.tutorial.MainActivity
import com.kaspersky.kaspresso.tutorial.R
import org.junit.Rule
import org.junit.Test

class SimpleActivityTest: TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test(){

        MainSceen{
            simpleActivityButton{
                isVisible()
                isClickable()
                click()
            }
        }

        SimpleActivityScreen{

            simpleTitle.isVisible()
            changeTitleButton.isClickable()
            simpleTitle.hasText(R.string.simple_activity_default_title)
            inputText.replaceText("New")
            changeTitleButton.click()
            simpleTitle.hasText("New")
        }

    }


}

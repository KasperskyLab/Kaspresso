package com.kaspersky.kaspresso.tutorial.differenttypes

import android.util.Log
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.DifferentTypesListScreen
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DifferentTypesListTest : TestCase() {

    @get:Rule
    val activityRule = ActivityScenarioRule(DifferentTypesListActivity::class.java)

    @Before
    fun setup() {
        Log.d("DEBDEB", "setup")
    }

    @Test
    fun checkExampleScreen() {
        before {
            //activityRule.scenario.moveToState(Lifecycle.State.INITIALIZED)
            activityRule.scenario.onActivity {
                // it.viewModel = viewModel
//                it.viewModel._uiState.update {
//                    it.copy(
//                        emptyList()
//                    )
//                }
            }
            Log.d("DEBDEB", "before")
        }.after {

        }.run {
//        step("Open note list screen") {
//            MainScreen {
//                listActivity2Button {
//                    isVisible()
//                    isClickable()
//                    click()
//                }
//            }
//        }
            step("Check notes count") {
                DifferentTypesListScreen {
                    //Assert.assertEquals("MESAGE", 11, _state.value.list.size)
                    //Assert.assertEquals("MESAGE", 11, viewModel.uiState.value.list.size)
                    Assert.assertEquals(11, rvNotes.getSize())
                }
            }
        }
    }
}

package com.kaspersky.kaspresso.tutorial.differenttypes

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.MainActivity
import com.kaspersky.kaspresso.tutorial.screen.DifferentTypesListScreen
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class DifferentTypesListTest : TestCase() {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun checkExampleScreen() = run {
        step("Open note list screen") {
            MainScreen {
                listActivity2Button {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Check list count") {
            DifferentTypesListScreen {
                Assert.assertEquals(10, rvNotes.getSize())
            }
        }
        step("Check elements visibility") {
            DifferentTypesListScreen {
                rvNotes {
                    children<DifferentTypesListScreen.NoteItemScreen> {
                        tvNoteId.isVisible()
                        tvNoteText.isVisible()
                        noteContainer.isVisible()

                        tvNoteId.hasAnyText()
                        tvNoteText.hasAnyText()
                    }
                }
            }
        }
        step("Check elements content") {
            DifferentTypesListScreen {
                rvNotes {
                    childAt<DifferentTypesListScreen.NoteItemScreen>(0) {
                        noteContainer.hasBackgroundColor(android.R.color.holo_green_light)
                        tvNoteId.hasText("0")
                        tvNoteText.hasText("Note number 0")
                    }
                    childAt<DifferentTypesListScreen.NoteItemScreen>(1) {
                        noteContainer.hasBackgroundColor(android.R.color.holo_orange_light)
                        tvNoteId.hasText("1")
                        tvNoteText.hasText("Note number 1")
                    }
                    childAt<DifferentTypesListScreen.NoteItemScreen>(2) {
                        noteContainer.hasBackgroundColor(android.R.color.holo_red_light)
                        tvNoteId.hasText("2")
                        tvNoteText.hasText("Note number 2")
                    }
                }
            }
        }
        step("Check swipe to dismiss action") {
            DifferentTypesListScreen {
                rvNotes {
                    childAt<DifferentTypesListScreen.NoteItemScreen>(0) {
                        swipeLeft()
                        device.uiDevice.waitForIdle()
                    }

                    Assert.assertEquals(9, getSize())

                    childAt<DifferentTypesListScreen.NoteItemScreen>(0) {
                        noteContainer.hasBackgroundColor(android.R.color.holo_orange_light)
                        tvNoteId.hasText("1")
                        tvNoteText.hasText("Note number 1")
                    }

                    childAt<DifferentTypesListScreen.NoteItemScreen>(1) {
                        noteContainer.hasBackgroundColor(android.R.color.holo_red_light)
                        tvNoteId.hasText("2")
                        tvNoteText.hasText("Note number 2")
                    }
                }
            }
        }
        step("Check Swipe Menu") {
            DifferentTypesListScreen {
                rvNotes {
                    childAt<DifferentTypesListScreen.MenuItemScreen>(5) {
                        tvNoteTextExpanded.isInvisible()
                    }
                    childAt<DifferentTypesListScreen.MenuItemScreen>(5) {
                        swipeLeft()
                        device.uiDevice.waitForIdle()
                    }
                    childAt<DifferentTypesListScreen.MenuItemScreen>(5) {
                        tvNoteTextExpanded.isVisible()
                    }
                }
            }
        }
    }

}

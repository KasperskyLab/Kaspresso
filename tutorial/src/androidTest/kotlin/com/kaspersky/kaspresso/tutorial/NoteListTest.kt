package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.NoteListScreen
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class NoteListTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkNotesScreen() = run {
        step("Open note list screen") {
            MainScreen {
                listActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Check notes count") {
            NoteListScreen {
                Assert.assertEquals(3, rvNotes.getSize())
            }
        }
        step("Check elements visibility") {
            NoteListScreen {
                rvNotes {
                    children<NoteListScreen.NoteItemScreen> {
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
            NoteListScreen {
                rvNotes {
                    childAt<NoteListScreen.NoteItemScreen>(0) {
                        noteContainer.hasBackgroundColor(android.R.color.holo_green_light)
                        tvNoteId.hasText("0")
                        tvNoteText.hasText("Note number 0")
                    }
                    childAt<NoteListScreen.NoteItemScreen>(1) {
                        noteContainer.hasBackgroundColor(android.R.color.holo_orange_light)
                        tvNoteId.hasText("1")
                        tvNoteText.hasText("Note number 1")
                    }
                    childAt<NoteListScreen.NoteItemScreen>(2) {
                        noteContainer.hasBackgroundColor(android.R.color.holo_red_light)
                        tvNoteId.hasText("2")
                        tvNoteText.hasText("Note number 2")
                    }
                }
            }
        }
        step("Check swipe to dismiss action") {
            NoteListScreen {
                rvNotes {
                    childAt<NoteListScreen.NoteItemScreen>(0) {
                        swipeLeft()
                        device.uiDevice.waitForIdle()
                    }

                    Assert.assertEquals(2, getSize())

                    childAt<NoteListScreen.NoteItemScreen>(0) {
                        noteContainer.hasBackgroundColor(android.R.color.holo_orange_light)
                        tvNoteId.hasText("1")
                        tvNoteText.hasText("Note number 1")
                    }

                    childAt<NoteListScreen.NoteItemScreen>(1) {
                        noteContainer.hasBackgroundColor(android.R.color.holo_red_light)
                        tvNoteId.hasText("2")
                        tvNoteText.hasText("Note number 2")
                    }
                }
            }
        }
    }
}

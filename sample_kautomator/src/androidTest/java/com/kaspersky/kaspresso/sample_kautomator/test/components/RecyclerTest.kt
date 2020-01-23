package com.kaspersky.kaspresso.sample_kautomator.test.components

import androidx.test.rule.ActivityTestRule
import com.kaspersky.kaspresso.sample_kautomator.recycler.RecyclerActivity
import com.kaspersky.kaspresso.sample_kautomator.screen.RecyclerScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class RecyclerTest : TestCase() {

    @get:Rule
    val rule = ActivityTestRule(RecyclerActivity::class.java, true, true)

    @Test
    fun test() {
        run {
            /**
             * An example of the use swipe gestures
             */
            step("Swipe actions") {
                RecyclerScreen {
                    /**
                     * Swipes up for about one screen
                     */
                    scroll { swipeUp() }
                    /**
                     * Top view should be not displayed now
                     */
                    top { isNotDisplayed() }
                    /**
                     * Swipe down for about one screen
                     */
                    scroll { swipeDown(); swipeDown() }
                    /**
                     * Top view should be displayed
                     */
                    top { isDisplayed() }
                }
            }

            step("Scroll actions") {
                RecyclerScreen {
                    /**
                     * Scrolls the view to the bottom
                     */
                    scroll { scrollToEnd() }
                    /**
                     * Bottom view should be displayed
                     */
                    bottom { isDisplayed() }
                    /**
                     * Scrolls the view to selected UiBaseView
                     */
                    scroll { scrollToView(this@RecyclerScreen.toSearch) }
                    /**
                     * toSearch view should be displayed
                     */
                    toSearch { isDisplayed() }
                    /**
                     * Scroll to top
                     */
                    scroll { scrollToStart() }
                    /**
                     * Top view should be displayed
                     */
                    top { isDisplayed() }
                }
            }
        }
    }
}
package com.kaspersky.kaspresso.composesupport.sample.test

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kaspersky.components.composesupport.config.withComposeSupport
import com.kaspersky.kaspresso.composesupport.sample.MainActivity
import com.kaspersky.kaspresso.composesupport.sample.resources.C
import com.kaspersky.kaspresso.composesupport.sample.screen.ComposeLazyListScreen
import com.kaspersky.kaspresso.composesupport.sample.screen.ComposeMainScreen
import com.kaspersky.kaspresso.composesupport.sample.screen.LazyListScreenItemNode
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import io.github.kakaocup.compose.node.element.ComposeScreen.Companion.onComposeScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComposeLazyListTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.withComposeSupport()
) {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun test() = run {
        step("Open LazyList screen") {
            onComposeScreen<ComposeMainScreen>(composeTestRule) {
                lazyListButton {
                    performClick()
                }
            }
        }
        step("Verify Lazy List") {
            onComposeScreen<ComposeLazyListScreen>(composeTestRule) {
                list {
                    assertIsDisplayed()
                    assertLengthEquals(C.Tag.scroll_screen_multi_text_items.size)

                    repeat(C.Tag.scroll_screen_multi_text_items.size) { i ->
                        childAt<LazyListScreenItemNode>(i) {
                            text1 {
                                assertIsDisplayed()
                                assertTextContains("text1_$i")
                            }
                            text2 {
                                assertIsDisplayed()
                                assertTextContains("text2_$i")
                            }
                            text3 {
                                assertIsDisplayed()
                                assertTextContains("text3_$i")
                            }
                        }
                    }
                }
            }
        }
    }
}

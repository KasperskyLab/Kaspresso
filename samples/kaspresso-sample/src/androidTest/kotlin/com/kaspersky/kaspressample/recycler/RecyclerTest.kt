package com.kaspersky.kaspressample.recycler

import androidx.fragment.app.testing.launchFragmentInContainer
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Test

class RecyclerTest : TestCase() {
    @Test
    fun test() = before {
        launchFragmentInContainer<RecyclerFragment>(themeResId = R.style.AppTheme)
    }.after {
    }.run {
        RecyclerScreen {
            recycler { scrollTo(15) }
            element14 { isDisplayed() }

            recycler { scrollToEnd() }
            element29 { isDisplayed() }

            recycler { scrollToStart() }
            element0 { isDisplayed() }
        }
    }
}

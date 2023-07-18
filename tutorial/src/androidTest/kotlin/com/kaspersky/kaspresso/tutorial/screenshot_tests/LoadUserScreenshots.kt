package com.kaspersky.kaspresso.tutorial.screenshot_tests

import androidx.fragment.app.testing.launchFragmentInContainer
import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase
import com.kaspersky.kaspresso.tutorial.R
import com.kaspersky.kaspresso.tutorial.screen.LoadUserScreen
import com.kaspersky.kaspresso.tutorial.user.LoadUserFragment
import com.kaspersky.kaspresso.tutorial.user.LoadUserViewModel
import com.kaspersky.kaspresso.tutorial.user.State
import com.kaspersky.kaspresso.tutorial.user.User
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Test

class LoadUserScreenshots : DocLocScreenshotTestCase(locales = "en, fr") {

    val _state = MutableStateFlow<State>(State.Initial)
    val viewModel = mockk<LoadUserViewModel>(relaxed = true) {
        every { state } returns _state
    }

    @Test
    fun takeScreenshots() {
        LoadUserScreen {
            launchFragmentInContainer(
                themeResId = R.style.Theme_Kaspresso
            ) {
                LoadUserFragment.newTestInstance(mockedViewModel = viewModel)
            }
            _state.value = State.Initial
            captureScreenshot("Initial state")
            _state.value = State.Progress
            captureScreenshot("Progress state")
            _state.value = State.Content(user = User(name = "Test", lastName = "Test"))
            captureScreenshot("Content state")
            _state.value = State.Error
            captureScreenshot("Error state")
        }
    }
}

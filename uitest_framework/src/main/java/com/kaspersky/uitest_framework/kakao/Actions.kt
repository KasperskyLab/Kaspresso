package com.kaspersky.uitest_framework.kakao

import android.net.Uri
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.TabLayout
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.*
import android.support.test.espresso.contrib.DrawerActions
import android.support.test.espresso.contrib.NavigationViewActions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.web.model.Atom
import android.support.test.espresso.web.model.ElementReference
import android.support.test.espresso.web.sugar.Web
import android.support.test.espresso.web.webdriver.DriverAtoms
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.InputDevice
import android.view.MotionEvent
import android.view.View
import android.widget.*
import com.agoda.kakao.PreciseSwipe
import com.kaspersky.uitest_framework.kakao.dispatchers.ViewDispatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher

/**
 * Created by egor.kurnikov on 04.03.2019
 */

/**
 * Interface with common actions for all screens
 *
 * Provides basic actions that can be performed on each and every screen
 *
 * @property view ViewInteraction on which all actions are performed (root view by default)
 */
interface ScreenActions {
    val view: ViewDispatcher

    /**
     * Performs click on device's back button
     */
    fun pressBack() {
        view.perform(ViewActions.pressBack())
    }

    /**
     * Closes soft keyboard, if opened
     */
    fun closeSoftKeyboard() {
        view.perform(ViewActions.closeSoftKeyboard())
    }

    /**
     * Presses a key with corresponding KeyCode
     */
    fun pressKey(keyCode: Int) {
        view.perform(ViewActions.pressKey(keyCode))
    }

    /**
     * Presses a key with correspondingKeyCode and modifiers
     */
    fun pressKey(key: EspressoKey) {
        view.perform(ViewActions.pressKey(key))
    }

    /**
     * Presses the hardware menu key
     */
    fun pressMenuKey() {
        view.perform(ViewActions.pressMenuKey())
    }

    /**
     * Idles for given amount of time
     *
     * @param duration Time to idle in milliseconds (1 second by default)
     */
    fun idle(duration: Long = 1000L) {
        view.perform(object : ViewAction {
            override fun getDescription() = "Idle for $duration milliseconds"

            override fun getConstraints() = ViewMatchers.isAssignableFrom(View::class.java)

            override fun perform(uiController: UiController?, view: View?) {
                uiController?.loopMainThreadForAtLeast(duration)
            }
        })
    }
}

/**
 * Base interface for performing actions on view
 *
 * Provides a lot of basic action methods, such as click(), scrollTo(), etc.
 *
 * @see EditableActions
 * @see SwipeableActions
 * @see ScrollableActions
 * @see CheckableActions
 */
interface BaseActions {
    val view: ViewDispatcher

    /**
     * Performs click on view
     *
     * @param location Location of view where it should be clicked (VISIBLE_CENTER by default)
     */
    fun click(location: GeneralLocation = GeneralLocation.VISIBLE_CENTER) {
        view.perform(GeneralClickAction(Tap.SINGLE, location, Press.FINGER,
                InputDevice.SOURCE_UNKNOWN, MotionEvent.BUTTON_PRIMARY))
    }

    /**
     * Performs double click on view
     *
     * @param location Location of view where it should be clicked (VISIBLE_CENTER by default)
     */
    fun doubleClick(location: GeneralLocation = GeneralLocation.VISIBLE_CENTER) {
        view.perform(GeneralClickAction(Tap.DOUBLE, location, Press.FINGER,
                InputDevice.SOURCE_UNKNOWN, MotionEvent.BUTTON_PRIMARY))
    }

    /**
     * Performs long click on view
     *
     * @param location Location of view where it should be clicked (VISIBLE_CENTER by default)
     */
    fun longClick(location: GeneralLocation = GeneralLocation.VISIBLE_CENTER) {
        view.perform(GeneralClickAction(Tap.LONG, location, Press.FINGER,
                InputDevice.SOURCE_UNKNOWN, MotionEvent.BUTTON_PRIMARY))
    }

    /**
     * Presses IME action, if supported view is in focus
     */
    fun pressImeAction() {
        view.perform(ViewActions.pressImeActionButton())
    }

    /**
     * Scrolls to the view, if possible
     */
    fun scrollTo() {
        view.perform(ViewActions.scrollTo())
    }

    /**
     * Performs custom action on a view
     *
     * @param function Lambda that must return ViewAction which will be performed
     */
    fun act(function: () -> ViewAction) {
        view.perform(function.invoke())
    }

    /**
     * Adds failure handler to the view
     *
     * @param function Lambda that handles this view's errors
     */
    fun onFailure(function: (error: Throwable, matcher: Matcher<View>) -> Unit) {
        view.withFailureHandler(function)
    }

    /**
     * Repeats given action on the view until this view will match the given matcher
     *
     * @param maxAttempts Maximum repeat count of the action
     * @param action Action to be performed
     * @param matcher ViewBuilder that will be used as matcher
     *
     * @see ViewActions.repeatedlyUntil
     */
    fun repeatUntil(maxAttempts: Int = 1, action: () -> ViewAction, matcher: ViewBuilder.() -> Unit) {
        view.perform(ViewActions.repeatedlyUntil(action(),
                ViewBuilder().apply(matcher).getViewMatcher(), maxAttempts))
    }
}

/**
 * Provides actions for TextViews
 */
interface TextViewActions : BaseActions {
    /**
     * @see ViewActions.openLinkWithText
     */
    fun openLinkWithText(text: String) {
        view.perform(ViewActions.openLinkWithText(text))
    }

    /**
     * @see ViewActions.openLinkWithText
     */
    fun openLinkWithText(text: Matcher<String>) {
        view.perform(ViewActions.openLinkWithText(text))
    }

    /**
     * @see ViewActions.openLinkWithUri
     */
    fun openLinkWithUri(uri: String) {
        view.perform(ViewActions.openLinkWithUri(uri))
    }

    /**
     * @see ViewActions.openLinkWithUri
     */
    fun openLinkWithUri(uri: Matcher<Uri>) {
        view.perform(ViewActions.openLinkWithUri(uri))
    }

    /**
     * @see ViewActions.openLink
     */
    fun openLink(text: Matcher<String>, uri: Matcher<Uri>) {
        view.perform(ViewActions.openLink(text, uri))
    }
}

/**
 * Provides editable actions for views
 */
interface EditableActions : BaseActions {
    /**
     * Types the given text into the view
     *
     * @param text Text to input
     */
    fun typeText(text: String) {
        view.perform(ViewActions.typeText(text))
    }

    /**
     * Replaces the current view text with given
     *
     * @param text Text to input instead of current
     */
    fun replaceText(text: String) {
        view.perform(ViewActions.replaceText(text))
    }

    /**
     * Clears current text in the view
     */
    fun clearText() {
        replaceText("")
    }
}

/**
 * Provides swipe actions for views
 */
interface SwipeableActions : BaseActions {
    /**
     * Swipes left on the view
     */
    fun swipeLeft() {
        view.perform(ViewActions.swipeLeft())
    }

    /**
     * Swipes right on the view
     */
    fun swipeRight() {
        view.perform(ViewActions.swipeRight())
    }

    /**
     * Swipes up on the view
     */
    fun swipeUp() {
        view.perform(ViewActions.swipeUp())
    }

    /**
     * Swipes down on the view
     */
    fun swipeDown() {
        view.perform(ViewActions.swipeDown())
    }
}

/**
 * Provides scrolling actions for view
 *
 * Important: does not hold any implementation
 *
 * @see RecyclerActions
 * @see ScrollViewActions
 */
interface ScrollableActions : BaseActions {
    /**
     * Scrolls to the starting position of the view
     */
    fun scrollToStart()

    /**
     * Scrolls to the last position of the view
     */
    fun scrollToEnd()

    /**
     * Scrolls to the specific position of the view
     *
     * @param position Scrolling destination
     */
    fun scrollTo(position: Int)
}

/**
 * Provides ScrollableActions implementation for RecyclerView
 *
 * @see ScrollableActions
 * @see SwipeableActions
 * @see RecyclerView
 */
interface RecyclerActions : ScrollableActions, SwipeableActions {
    override fun scrollToStart() {
        view.perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
    }

    override fun scrollToEnd() {
        view.perform(object : ViewAction {
            override fun getDescription() = "Scroll RecyclerView to the bottom"

            override fun getConstraints() = ViewMatchers.isAssignableFrom(RecyclerView::class.java)

            override fun perform(controller: UiController, view: View) {
                if (view is RecyclerView) {
                    val position = view.adapter!!.itemCount - 1
                    view.scrollToPosition(position)
                    controller.loopMainThreadUntilIdle()
                    val lastView = view.findViewHolderForLayoutPosition(position)!!.itemView
                    view.scrollBy(0, lastView.height)
                    controller.loopMainThreadUntilIdle()
                }
            }
        })
    }

    override fun scrollTo(position: Int) {
        view.perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position))
    }

    /**
     * Scrolls to specific view holder that matches given matcher
     *
     * @param matcher Matcher for view holder, which is scroll destination
     */
    fun scrollTo(matcher: Matcher<View>) {
        view.perform(RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(matcher))
    }

    /**
     * Scrolls to specific view holder that matches given matcher
     *
     * @param viewBuilder Builder that will be used to match view to scroll
     */
    fun scrollTo(viewBuilder: ViewBuilder.() -> Unit) {
        scrollTo(ViewBuilder().apply { withIndex(0, viewBuilder) }.getViewMatcher())
    }

    /**
     * Returns the size of RecyclerView
     *
     * @return size of adapter
     *
     * @see RecyclerView
     */
    fun getSize(): Int {
        var size = 0

        view.perform(object : ViewAction {
            override fun getDescription() = "Get RecyclerView adapter size"

            override fun getConstraints() = Matchers.allOf(ViewMatchers
                    .isAssignableFrom(RecyclerView::class.java), ViewMatchers.isDisplayed())

            override fun perform(uiController: UiController?, view: View?) {
                if (view is RecyclerView) {
                    size = view.adapter?.itemCount!!
                }
            }
        })

        return size
    }
}

/**
 * Provides ScrollableActions implementation for ScrollView
 *
 * @see ScrollableActions
 * @see SwipeableActions
 * @see ScrollView
 */
interface ScrollViewActions : ScrollableActions, SwipeableActions {
    override fun scrollToStart() {
        view.perform(object : ViewAction {
            override fun getDescription() = "Scroll ScrollView to start"

            override fun getConstraints() = Matchers.allOf(ViewMatchers
                    .isAssignableFrom(ScrollView::class.java), ViewMatchers.isDisplayed())

            override fun perform(uiController: UiController?, view: View?) {
                if (view is ScrollView) {
                    view.fullScroll(View.FOCUS_UP)
                }
            }
        })
    }

    override fun scrollToEnd() {
        view.perform(object : ViewAction {
            override fun getDescription() = "Scroll ScrollView to end"

            override fun getConstraints() = Matchers.allOf(ViewMatchers
                    .isAssignableFrom(ScrollView::class.java), ViewMatchers.isDisplayed())

            override fun perform(uiController: UiController?, view: View?) {
                if (view is ScrollView) {
                    view.fullScroll(View.FOCUS_DOWN)
                }
            }
        })
    }

    override fun scrollTo(position: Int) {
        view.perform(object : ViewAction {
            override fun getDescription() = "Scroll ScrollView to $position Y position"

            override fun getConstraints() = Matchers.allOf(ViewMatchers
                    .isAssignableFrom(ScrollView::class.java), ViewMatchers.isDisplayed())

            override fun perform(uiController: UiController?, view: View?) {
                if (view is ScrollView) {
                    view.scrollTo(0, position)
                }
            }
        })
    }

    /**
     * Returns the size of ScrollView
     *
     * @return size of adapter
     *
     * @see ScrollView
     * @see AdapterView
     */
    fun getSize(): Int {
        var size = 0

        view.perform(object : ViewAction {
            override fun getDescription() = "Get AdapterView adapter size"

            override fun getConstraints() = Matchers.allOf(ViewMatchers
                    .isAssignableFrom(AdapterView::class.java), ViewMatchers.isDisplayed())

            override fun perform(uiController: UiController?, view: View?) {
                if (view is AdapterView<*>) {
                    size = view.count
                }
            }
        })

        return size
    }
}

/**
 * Provides action for checking views
 */
interface CheckableActions : BaseActions {
    /**
     * Sets checked state of the view
     *
     * @param checked True if checked, false otherwise
     */
    fun setChecked(checked: Boolean) {
        view.perform(object : ViewAction {
            override fun getDescription() = "performing CheckableViewAction: $checked"

            override fun getConstraints() = Matchers.allOf(ViewMatchers.isAssignableFrom(View::class.java),
                    object : TypeSafeMatcher<View>() {
                        override fun describeTo(description: Description) {
                            description.appendText("is assignable from class: " + Checkable::class.java)
                        }

                        override fun matchesSafely(view: View) = Checkable::class.java.isAssignableFrom(view.javaClass)
                    })

            override fun perform(uiController: UiController, view: View) {
                if (view is Checkable) view.isChecked = checked
            }
        })
    }
}

/**
 * Provides actions for navigation drawer
 */
interface DrawerActions : BaseActions {
    /**
     * Opens the navigation drawer
     *
     * @param gravity Gravity to use (Gravity.START by default)
     * @see Gravity.START
     */
    fun open(gravity: Int = Gravity.START) {
        view.perform(DrawerActions.open(gravity))
    }

    /**
     * Closes the navigation drawer
     *
     * @param gravity Gravity to use (Gravity.START by default)
     * @see Gravity.START
     */
    fun close(gravity: Int = Gravity.START) {
        view.perform(DrawerActions.close(gravity))
    }
}

/**
 * Provides actions for navigation view
 */
interface NavigationViewActions : BaseActions {
    /**
     * Clicks on the navigation view menu item with given id
     *
     * @param id Menu id to be navigated to
     */
    fun navigateTo(id: Int) {
        view.perform(NavigationViewActions.navigateTo(id))
    }
}

/**
 * Provides action for ProgressBar
 */
interface ProgressBarActions : BaseActions {
    /**
     * Set progress for ProgressBar
     *
     * @param number of progress to set for the ProgressBar
     */
    fun setProgress(number: Int) {
        view.perform(object : ViewAction {
            override fun getDescription() = "set progress value of progress bar as: $number"

            override fun getConstraints() = ViewMatchers.isAssignableFrom(ProgressBar::class.java)

            override fun perform(uiController: UiController, view: View) {
                if (view is ProgressBar) {
                    view.progress = number
                }
            }
        })
    }
}

/**
 * Provides action for SeekBar
 */
interface SeekBarActions : ProgressBarActions {
    /**
     * Drags progress to defined position.
     * Please note that this dragging is emulated via Espresso's swipe action
     * and might not be accurate, if progress max value is too high or device's
     * density is too low.
     *
     * @param number of progress to drag to
     *
     * @see GeneralSwipeAction
     */
    fun dragProgressTo(number: Int) {
        view.perform(object : ViewAction {
            override fun getDescription() = "drags progress of seek bar to: $number"

            override fun getConstraints() = ViewMatchers.isAssignableFrom(SeekBar::class.java)

            override fun perform(uiController: UiController, view: View) {
                if (view is SeekBar) {
                    view.takeIf { it.width > 0 }?.run {
                        val position = IntArray(2).apply {
                            view.getLocationOnScreen(this)
                        }

                        val realWidth = (width - paddingLeft - paddingRight).toFloat()
                        val realHeight = (height - paddingTop - paddingBottom).toFloat()

                        position[0] += paddingLeft
                        position[1] += paddingTop

                        val start = CoordinatesProvider {
                            floatArrayOf(
                                    position[0].toFloat() + realWidth / max * progress,
                                    position[1].toFloat() + realHeight / 2
                            )
                        }

                        val end = CoordinatesProvider {
                            floatArrayOf(
                                    position[0].toFloat() + realWidth / max * number,
                                    position[1].toFloat() + realHeight / 2
                            )
                        }

                        GeneralSwipeAction(PreciseSwipe, start, end, Press.PINPOINT)
                                .perform(uiController, view)
                    }
                }
            }
        })
    }
}

/**
 * Provides action for RatingBar
 */
interface RatingBarActions : BaseActions {
    /**
     * Set rating for RatingBar
     *
     * @param number rating to set for the RatingBar
     */
    fun setRatingAt(number: Float) {
        view.perform(object : ViewAction {
            override fun getDescription() = "set rating value of rating bar as: $number"

            override fun getConstraints() = ViewMatchers.isAssignableFrom(RatingBar::class.java)

            override fun perform(uiController: UiController, view: View) {
                if (view is RatingBar) {
                    view.rating = number
                }
            }
        })
    }
}

/**
 * Provides actions for BottomNavigationView
 */
interface BottomNavigationViewActions : BaseActions {
    /**
     * Sets the given item id as selected
     *
     * @param id menu item id to be set
     */
    fun setSelectedItem(id: Int) {
        view.perform(object : ViewAction {
            override fun getDescription() = "Sets given item id as selected: $id"

            override fun getConstraints() = ViewMatchers
                    .isAssignableFrom(BottomNavigationView::class.java)

            override fun perform(uiController: UiController, view: View) {
                if (view is BottomNavigationView) {
                    view.selectedItemId = id
                }
            }
        })
    }
}

/**
 * Provides action for TabLayout
 */
interface TabLayoutActions : BaseActions {
    /**
     * Selects tab at given index
     *
     * @param index tab index to be selected
     */
    fun selectTab(index: Int) {
        view.perform(object : ViewAction {
            override fun getDescription() = "Selects the tab at index: $index"

            override fun getConstraints() = ViewMatchers.isAssignableFrom(TabLayout::class.java)

            override fun perform(uiController: UiController, view: View) {
                if (view is TabLayout) {
                    view.getTabAt(index)!!.select()
                }
            }
        })
    }

    /**
     * Returns the currently selected item id
     *
     * @return selected menu item id
     */
    fun getSelectedItem(): Int {
        var id = 0

        view.perform(object : ViewAction {
            override fun getDescription() = "Gets selected item id"

            override fun getConstraints() = ViewMatchers
                    .isAssignableFrom(BottomNavigationView::class.java)

            override fun perform(uiController: UiController, view: View) {
                if (view is BottomNavigationView) {
                    id = view.selectedItemId
                }
            }
        })

        return id
    }
}

/**
 * Provides actions for SwipeRefreshLayout
 */
interface SwipeRefreshLayoutActions : SwipeableActions {
    /**
     * Sets the refreshing state of SwipeRefreshLayout
     *
     * @param refreshing state to be set
     */
    fun setRefreshing(refreshing: Boolean) {
        view.perform(object : ViewAction {
            override fun getDescription() = "Sets the refreshing state to $refreshing"

            override fun getConstraints() = ViewMatchers.isAssignableFrom(SwipeRefreshLayout::class.java)

            override fun perform(uiController: UiController, view: View) {
                if (view is SwipeRefreshLayout) {
                    view.isRefreshing = refreshing
                }
            }
        })
    }
}

/**
 * Provides action for interacting with WebViews
 *
 * @see WebView
 */
interface WebActions {
    val web: Web.WebInteraction<*>
    val ref: Atom<ElementReference>

    /**
     * Clicks on element
     */
    fun click() {
        perform(DriverAtoms.webClick())
    }

    /**
     * Input keys with element in focus
     *
     * @param text Text to input
     */
    fun keys(text: String) {
        perform(DriverAtoms.webKeys(text))
    }

    /**
     * Scrolls to the element inside WebView
     */
    fun scroll() {
        perform(DriverAtoms.webScrollIntoView())
    }

    /**
     * Clears the element
     */
    fun clear() {
        perform(DriverAtoms.clearElement())
    }

    /**
     * Performs custom action on element
     *
     * @param action Action to be performed
     */
    private fun perform(action: Atom<*>) {
        web.withElement(ref).perform(action)
    }
}
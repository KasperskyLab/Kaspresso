package com.kaspersky.kaspressample.screen

import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.robolectric.RobolectricActivity
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.scroll.KScrollView
import io.github.kakaocup.kakao.text.KButton

class RobolectricScreen : KScreen<RobolectricScreen>() {

    override val layoutId: Int = R.layout.activity_robolectric
    override val viewClass: Class<*> = RobolectricActivity::class.java

    val findMeButton = KButton { withId(R.id.findMeButton) }

    val scrollView = KScrollView { withId(R.id.scrollMe) }

    val firstNameEditText = KEditText { withId(R.id.firstName) }
    val lastNameEditText = KEditText { withId(R.id.lastName) }
    val ageEditText = KEditText { withId(R.id.age) }

    val maleButton = KButton { withId(R.id.male) }

    fun writeFirstName(firstName: String) {
        firstNameEditText.replaceText(firstName)
    }

    fun writeLastName(lastName: String) {
        lastNameEditText.replaceText(lastName)
    }

    fun writeAge(age: String) {
        ageEditText.replaceText(age)
    }

    fun clickOnMale() {
        maleButton.click()
    }

    fun clickOnFindMeButton() {
        findMeButton.click()
    }

    fun verifyFirstNameIs(firstName: String) {
        firstNameEditText.hasText(firstName)
    }

    fun isButtonDisplayed() {
        findMeButton.isDisplayed()
    }
}

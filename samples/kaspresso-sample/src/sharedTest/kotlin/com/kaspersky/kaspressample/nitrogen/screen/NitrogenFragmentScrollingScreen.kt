package com.kaspersky.kaspressample.nitrogen.screen

import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.text.KButton
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.nitrogen.NitrogenActivity
import com.kaspersky.kaspresso.screens.KScreen

class NitrogenFragmentScrollingScreen : KScreen<NitrogenFragmentScrollingScreen>() {

    override val layoutId: Int? = R.layout.activity_nitrogen
    override val viewClass: Class<*>? = NitrogenActivity::class.java

    val findMeButton = KButton { withId(R.id.findMeButton) }
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

    fun verifyLastNameIs(firstName: String) {
        lastNameEditText.hasText(firstName)
    }
}

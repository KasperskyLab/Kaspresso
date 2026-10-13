package com.kaspersky.kaspressample.screen

import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.localization.LocalizationExerciseActivity
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView

object LocalizationExerciseScreen : KScreen<LocalizationExerciseScreen>() {
    override val layoutId: Int = R.layout.activity_localization_exercise
    override val viewClass: Class<*> = LocalizationExerciseActivity::class.java

    val heading = KTextView { withId(R.id.localization_heading) }
    val description = KTextView { withId(R.id.localization_description) }
    val nameInput = KEditText { withId(R.id.localization_name_input) }
    val emailInput = KEditText { withId(R.id.localization_email_input) }
    val saveButton = KButton { withId(R.id.localization_save_button) }
    val cancelButton = KButton { withId(R.id.localization_cancel_button) }
    val savedMessage = KTextView { withId(R.id.localization_saved_message) }
}

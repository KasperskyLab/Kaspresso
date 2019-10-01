package com.kaspersky.kaspresso.obtain_value.actions

import androidx.test.espresso.ViewAction

interface ObtainValueViewAction : ViewAction {
    var value: String
}
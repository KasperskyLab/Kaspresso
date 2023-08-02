package com.kaspersky.kaspresso.systemsafety

import java.util.regex.Pattern

enum class SystemDialogSafetyPattern(val pattern: Pattern) {
    PERMISSION_API23(Pattern.compile("\\S*android.packageinstaller\\S*")),
    PERMISSION_API30(Pattern.compile("\\S*android.permissioncontroller\\S*")),
    OTHER(Pattern.compile("\\S*google.android\\S*"))
}

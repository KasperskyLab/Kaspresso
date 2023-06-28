package com.kaspersky.kaspresso.systemsafety

import java.util.regex.Pattern

enum class SystemSafetyPattern(val pattern: Pattern) {
    API23(Pattern.compile("\\S*android.packageinstaller\\S*")),
    OTHER(Pattern.compile("\\S*google.android\\S*"))
}

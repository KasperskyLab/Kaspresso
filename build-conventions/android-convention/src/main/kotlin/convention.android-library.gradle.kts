plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("convention.android-base")
}

android {
    variantFilter {
        if (name != "release") {
            ignore = true
        }
    }

    @Suppress("UnstableApiUsage")
    onVariants {
        androidTest {
            enabled = false
        }
    }
}

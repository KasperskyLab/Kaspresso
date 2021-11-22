plugins {
    id("com.android.library")
    id("kotlin-android")
    id("convention.kotlin-base")
    id("convention.android-base")
}

androidComponents {
    beforeVariants {
        it.enableAndroidTest = false
    }
}

android {
    variantFilter {
        if (name != "release") {
            ignore = true
        }
    }
}

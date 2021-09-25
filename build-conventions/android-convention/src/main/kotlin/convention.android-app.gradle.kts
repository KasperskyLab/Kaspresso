plugins {
    id("com.android.application")
    id("kotlin-android")
    id("convention.android-base")
}

android {
    testBuildType = "debug"

    variantFilter {
        if (name != testBuildType) {
            ignore = true
        }
    }

    buildTypes {
        getByName(testBuildType) {
            matchingFallbacks += listOf("release")
        }
    }
}

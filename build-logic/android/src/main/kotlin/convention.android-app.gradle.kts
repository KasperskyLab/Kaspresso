plugins {
    id("com.android.application")
    id("kotlin-android")
    id("convention.kotlin-base")
    id("convention.android-base")
}

android {
    testBuildType = "debug"

    defaultConfig {
        multiDexEnabled = true
    }

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

    @Suppress("UnstableApiUsage")
    buildFeatures {
        viewBinding = true // for the samples
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

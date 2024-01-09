plugins {
    id("com.android.application")
    id("convention.kotlin-base")
    id("convention.android-base")
    kotlin("android")
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

    kotlin {
        jvmToolchain(JavaVersion.VERSION_17.majorVersion.toInt())
    }
}

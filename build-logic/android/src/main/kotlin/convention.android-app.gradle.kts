import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    id("convention.kotlin-base")
    id("convention.android-base")
    kotlin("android")
}

android {
    compileOptions {
        targetCompatibility = JavaVersion.VERSION_17
        sourceCompatibility = JavaVersion.VERSION_17
    }

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

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

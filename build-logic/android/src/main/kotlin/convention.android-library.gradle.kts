import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.library")
    id("convention.kotlin-base")
    id("convention.android-base")
    kotlin("android")
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

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

kotlin {
    jvmToolchain(8)
}

plugins {
    id("convention.android-app")
}

android {
    defaultConfig {
        applicationId = "com.kaspersky.kaspresso.sample_upgrade_tests"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(libs.material)
    implementation(libs.multidex)

    androidTestImplementation(projects.kaspresso)
}

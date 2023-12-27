plugins {
    id("convention.android-app")
}

android {
    namespace = "com.kaspersky.kaspresso.upgradesample"
    defaultConfig {
        applicationId = "com.kaspersky.kaspresso.sample_upgrade_tests"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.multidex)

    androidTestImplementation(projects.kaspresso)
}

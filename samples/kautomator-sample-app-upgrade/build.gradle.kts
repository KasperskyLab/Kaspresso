plugins {
    id("convention.android-app")
    id("com.kaspersky.kaspresso-adb-server-plugin")
}

android {
    defaultConfig {
        applicationId = "com.kaspersky.kaspresso.sample_upgrade_tests"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.multidex)

    androidTestImplementation("com.kaspersky.android-components:kaspresso")
}

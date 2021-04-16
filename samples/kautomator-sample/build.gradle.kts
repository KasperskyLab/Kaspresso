import Dependencies.Versions

plugins {
    androidApplication
    kotlinAndroid
    kotlinAndroidExtensions
}

android {
    compileSdkVersion(Versions.compileSdk)
    buildToolsVersion(Versions.buildTools)

    defaultConfig {
        applicationId = "com.kaspersky.kaspresso.kautomatorsample"
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraint)

    androidTestImplementation(libs.uiAutomator)
    androidTestImplementation(libs.androidXRules)
    androidTestImplementation(projects.kaspresso)
    androidTestImplementation(projects.kautomator)
}

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
        applicationId = "com.kaspersky.kaspresso.sample_upgrade_tests"
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(Dependencies.appcompat)

    androidTestImplementation(Dependencies.uiAutomator)
    androidTestImplementation(Dependencies.rules)
    androidTestImplementation(project(Projects.Kaspresso.framework))
    androidTestImplementation(project(Projects.Kautomator.framework))
}
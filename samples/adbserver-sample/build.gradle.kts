import Dependencies.Versions

plugins {
    androidApplication
    kotlinAndroid
}

android {
    compileSdkVersion(Versions.compileSdk)
    buildToolsVersion(Versions.buildTools)

    defaultConfig {
        applicationId = "com.kaspersky.adbserver.sample"

        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)
    }
}

dependencies {
    implementation(libs.kotlinStdlib)
    implementation(libs.appcompat)
    implementation(projects.adbServer.adbserverDevice) { isTransitive = false }
}

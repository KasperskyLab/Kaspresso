import Dependencies.Versions

plugins {
    androidApplication
    kotlinAndroid
}

android {
    compileSdkVersion(Versions.compileSdk)
    buildToolsVersion(Versions.buildTools)

    defaultConfig {
        applicationId = "com.kaspersky.testserver"

        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)
    }
}

dependencies {
    implementation(Dependencies.kotlinStdlib)
    implementation(Dependencies.appcompat)
    implementation(project(Projects.AdbServer.common))
    implementation(project(Projects.AdbServer.device))
}
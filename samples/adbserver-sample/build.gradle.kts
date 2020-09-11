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
    implementation(Dependencies.kotlinStdlib)
    implementation(Dependencies.appcompat)
    implementation(project(Projects.AdbServer.device)) { isTransitive = false }
}

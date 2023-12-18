plugins {
    id("convention.android-app")
}

android {
    defaultConfig {
        applicationId = "com.kaspersky.adbserver.sample"
    }
}

dependencies {
    implementation(libs.kotlinStdlib)
    implementation(libs.appcompat)
    implementation(projects.adbServer.adbServerDevice)
    implementation(libs.multidex)
}

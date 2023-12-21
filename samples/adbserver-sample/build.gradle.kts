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
    implementation("com.kaspersky.android-components:adb-server-device")
    implementation(libs.multidex)
}

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
    implementation("com.kaspersky.android-components:adbserver-device")
    implementation(libs.multidex)
}

plugins {
    id("convention.android-library")
}

dependencies {
    implementation(libs.kotlinStdlib)
    implementation(libs.truth)
    implementation(libs.testCore)
    implementation(libs.uiAutomator)
}

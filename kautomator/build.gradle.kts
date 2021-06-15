plugins {
    id("convention.android-library")
    id("convention.publication-android-lib")
}

publish {
    artifactId.set("kautomator")
}

dependencies {
    implementation(libs.kotlinStdlib)
    implementation(libs.truth)
    implementation(libs.testCore)
    implementation(libs.uiAutomator)
}

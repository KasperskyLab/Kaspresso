plugins {
    id("convention.android-library")
    id("convention.publication-android-lib")
    id("convention.third-party-report")
    id("convention.legal-documents")
}

android {
    namespace = "com.kaspersky.components.kautomator"
}

publish {
    artifactId.set("kautomator")
}

dependencies {
    implementation(libs.kotlinStdlib)
    implementation(libs.truth)
    implementation(libs.androidXTestCore)
    implementation(libs.uiAutomator)
}

plugins {
    id("convention.android-library")
    id("convention.publication-android-lib")
    id("convention.third-party-report")
    id("convention.legal-documents")
}

publish {
    artifactId.set("page-object-code-gen")
}

dependencies {
    implementation(libs.kotlinStdlib)
    implementation(libs.androidXTestCore)
    implementation(libs.uiAutomator)
    testImplementation(libs.junit)
    testImplementation(libs.truth)
    testImplementation(libs.assertj)
}

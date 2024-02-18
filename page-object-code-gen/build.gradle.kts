plugins {
    id("convention.android-library")
    id("convention.publication-android-lib")
    id("convention.third-party-report")
    id("convention.legal-documents")
    id("com.github.johnrengelman.shadow") version "6.0.0"
}

dependencies {
    api(libs.kotlinStdlib)
    api(libs.androidXTestCore)
    api(libs.uiAutomator)
    api(libs.kakao)
    testImplementation(libs.junit)
    testImplementation(libs.truth)
    testImplementation(libs.assertj)
}

tasks.withType<Jar>() {
    manifest {
        attributes["Main-Class"] = "com.kaspersky.components.pageobjectcodegen.CreatePageObjectFromUiDumpKt"
    }
}

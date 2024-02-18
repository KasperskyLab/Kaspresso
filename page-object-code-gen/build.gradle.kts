plugins {
    id("convention.android-library")
    id("convention.publication-android-lib")
    id("convention.third-party-report")
    id("convention.legal-documents")
}

dependencies {
    api(libs.kakao)
    testImplementation(libs.junit)
    testImplementation(libs.assertj)
}

tasks.withType<Jar>() {
    manifest {
        attributes["Main-Class"] = "com.kaspersky.components.pageobjectcodegen.CreatePageObjectFromUiDumpKt"
    }
}

plugins {
    id("convention.android-library")
    id("convention.publication-android-lib")
    id("convention.third-party-report")
    id("convention.legal-documents")
}

android {
    namespace = "com.kaspersky.kaspresso"
}

publish {
    artifactId.set("kaspresso")
}

dependencies {
    api(projects.kautomator)
    api(libs.kakao)
    api(libs.kakaoExtClicks)
    api(libs.bundles.espresso)
    api(libs.uiAutomator)
    api(libs.androidXCore)
    api(libs.androidXTestRules)

    implementation(libs.kotlinStdlib)
    implementation(libs.gson)
    implementation(projects.adbServer.adbServerDevice)
    implementation(libs.appcompat)
    implementation(libs.material)

    testImplementation(libs.junit)
    testImplementation(libs.truth)
}

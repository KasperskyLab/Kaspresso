plugins {
    id("convention.android-library")
    id("convention.publication-android-lib")
}

publish {
    artifactId.set("kaspresso")
}

dependencies {
    api(projects.kautomator)
    api(libs.kakao)
    api(libs.kakaoCompose)
    api(libs.bundles.espresso)
    api(libs.uiAutomator)
    api(libs.androidXCore)
    api(libs.androidXRules)

    implementation(libs.kotlinStdlib)
    implementation(libs.gson)
    implementation(projects.adbServer.adbserverDevice)
    implementation(libs.composeUiTest)

    testImplementation(libs.junit)
    testImplementation(libs.truth)
}

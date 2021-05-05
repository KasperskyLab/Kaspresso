plugins {
    id("convention.android-library")
}

dependencies {
    api(fileTree("libs").matching { include("*.jar") })

    implementation(libs.kotlinStdlib)
    implementation(libs.uiAutomator)
    implementation(libs.androidXCore)
    implementation(libs.androidXRules)
    implementation(libs.kakao)
    implementation(libs.gson)
    implementation(libs.bundles.espresso)

    implementation(projects.kautomator)
    implementation(projects.adbServer.adbserverDevice) { isTransitive = false }

    testImplementation(libs.junit)
    testImplementation(libs.truth)
}

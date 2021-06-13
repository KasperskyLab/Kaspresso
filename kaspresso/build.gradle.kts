plugins {
    id("convention.android-library")
    id("convention.publication-android-lib")
}

publish {
    artifactId.set("kaspresso")
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
    implementation(projects.adbServer.adbserverDevice)

    testImplementation(libs.junit)
    testImplementation(libs.truth)
}

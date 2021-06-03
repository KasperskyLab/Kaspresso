plugins {
    id("convention.android-app")
}

android {
    defaultConfig {
        applicationId = "com.kaspersky.kaspressample"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments["clearPackageData"] = "true"
    }

    testOptions {
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
    }
}

dependencies {
    implementation(project(mapOf("path" to ":samples:kaspresso-sample-core")))
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraint)

    androidTestImplementation(libs.runner)
    androidTestImplementation(libs.androidXRules)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.espressoCore)
    androidTestImplementation(libs.espressoWeb)
    androidTestImplementation(libs.kakao)
    androidTestImplementation(projects.kaspresso)
    androidTestImplementation(projects.kautomator)

    androidTestUtil(libs.orchestrator)
}

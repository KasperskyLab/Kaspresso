plugins {
    id("convention.android-app")
    id("com.kaspersky.kaspresso")
}

android {
    defaultConfig {
        applicationId = "com.kaspersky.kaspresso.kautomatorsample"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments["clearPackageData"] = "true"
    }

    testOptions {
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraint)
    implementation(libs.multidex)

    androidTestImplementation("com.kaspersky.android-components:kaspresso")
    androidTestImplementation(libs.androidXTestExtJunitKtx)
    androidTestImplementation(libs.androidXTestExtJunit)

    androidTestUtil(libs.androidXTestOrchestrator)
}

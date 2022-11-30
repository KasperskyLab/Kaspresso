plugins {
    id("convention.android-app")
}

android {
    defaultConfig {
        applicationId = "com.kaspersky.kaspresso.alluresupport.sample"
        testInstrumentationRunner = "com.kaspersky.kaspresso.runner.KaspressoRunner"
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

    androidTestImplementation(projects.kaspresso)
    androidTestImplementation(projects.allureSupport)

    androidTestImplementation(libs.androidXTestExtJunitKtx)
    androidTestImplementation(libs.androidXTestExtJunit)
    androidTestImplementation(libs.junit)

    androidTestUtil(libs.androidXTestOrchestrator)
}

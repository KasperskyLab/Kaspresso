plugins { id("convention.android-app") }

android {
    namespace = "com.kaspersky.kaspresso.kautomatorsample.compiletimertest"
    defaultConfig {
        applicationId = "com.kaspersky.kaspresso.kautomatorsample.compiletimertest"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments["clearPackageData"] = "true"
    }
    testOptions { execution = "ANDROIDX_TEST_ORCHESTRATOR" }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraint)
    androidTestImplementation(projects.kaspresso)
    androidTestImplementation(libs.androidXTestExtJunitKtx)
    androidTestImplementation(libs.androidXTestExtJunit)
    androidTestUtil(libs.androidXTestOrchestrator)
}

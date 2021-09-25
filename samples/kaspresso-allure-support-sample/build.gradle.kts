plugins {
    id("convention.android-app")
}

android {
    defaultConfig {
        applicationId = "com.kaspersky.kaspresso.allure_support"
        // AllureAndroidJUnitRunner must be used as testInstrumentationRunner
        testInstrumentationRunner = "io.qameta.allure.android.runners.AllureAndroidJUnitRunner"
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

    androidTestImplementation(projects.kaspresso)
    androidTestImplementation(projects.kautomator)
    androidTestImplementation(projects.allureSupport)

    androidTestImplementation(libs.runner)
    androidTestImplementation(libs.androidXRules)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.kakao)
    androidTestImplementation(libs.bundles.espresso)

    androidTestUtil(libs.orchestrator)
}

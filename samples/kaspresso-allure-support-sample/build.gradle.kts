plugins {
    id("convention.android-app")
}

android {
    defaultConfig {
        applicationId = "com.kaspersky.kaspresso_allure_support_sample"
        testInstrumentationRunner = "com.kaspersky.components.allure_support.runner.KaspressoTestRunner"
        testInstrumentationRunnerArguments["clearPackageData"] = "true"
    }

    testOptions {
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
    }
}

repositories {
    maven { url = uri("https://dl.bintray.com/qameta/maven") }
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

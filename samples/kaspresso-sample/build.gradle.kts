import Dependencies.Versions

plugins {
    androidApplication
    kotlinAndroid
    kotlinAndroidExtensions
}

android {
    compileSdkVersion(Versions.compileSdk)
    buildToolsVersion(Versions.buildTools)

    defaultConfig {
        applicationId = "com.kaspersky.kaspressample"

        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments["clearPackageData"] = "true"
    }

    sourceSets {
        val main by getting {
            java.srcDir("src/main/kotlin")
        }
    }

    testOptions {
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
    }
}

dependencies {
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

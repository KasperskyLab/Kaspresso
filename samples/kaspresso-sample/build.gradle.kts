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
        testInstrumentationRunnerArguments = mapOf("clearPackageData" to "true")
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
    implementation(project(mapOf("path" to ":samples:kaspresso-sample-core")))
    implementation(Dependencies.appcompat)
    implementation(Dependencies.material)
    implementation(Dependencies.constraint)

    androidTestImplementation(project(Projects.Kaspresso.framework))
    androidTestImplementation(project(Projects.Kautomator.framework))

    androidTestImplementation(Dependencies.runner)
    androidTestImplementation(Dependencies.rules)
    androidTestImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.espressoCore)
    androidTestImplementation(Dependencies.espressoWeb)
    androidTestImplementation(Dependencies.kakao)

    androidTestUtil(Dependencies.orchestrator)
}

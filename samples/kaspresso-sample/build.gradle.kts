plugins {
    id("convention.android-app")
}

android {
    defaultConfig {
        applicationId = "com.kaspersky.kaspressample"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments["clearPackageData"] = "true"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerVersion = libs.versions.kotlin.get()
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }

    sourceSets {
        // configure shared test folder
        val sharedTestFolder = "src/sharedTest/kotlin"
        named("test").configure { java.srcDirs("src/test/kotlin", sharedTestFolder) }
        named("androidTest").configure { java.srcDirs("src/androidTest/kotlin", sharedTestFolder) }
    }

    testOptions {
        execution = "ANDROIDX_TEST_ORCHESTRATOR"

        // allow to run tests on JVM
        unitTests.isReturnDefaultValues = true
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraint)
    implementation(libs.bundles.compose)
    implementation(libs.lifecycleViewModelKtx)
    implementation(libs.lifecycleLiveDataKtx)
    implementation(libs.composeNavigation)
    implementation(libs.lifecycleViewModelComposeKtx)
    implementation(libs.composeRuntimeLiveData)

    androidTestImplementation(libs.runner)
    androidTestImplementation(libs.junit)
    androidTestImplementation(projects.kaspresso)
    androidTestImplementation(libs.androidXRules)
    androidTestImplementation(libs.androidXTestKtx)
    androidTestImplementation(libs.androidXTest)
    androidTestImplementation(libs.composeJunit)

    androidTestUtil(libs.orchestrator)

    testImplementation(libs.runner)
    testImplementation(libs.junit)
    testImplementation(projects.kaspresso)
    testImplementation(libs.androidXRules)
    testImplementation(libs.androidXTestKtx)
    testImplementation(libs.androidXTest)
    testImplementation(libs.robolectric)
    testImplementation(libs.composeJunit)

    debugImplementation(libs.fragmentTesting)
}

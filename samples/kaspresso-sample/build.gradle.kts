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
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }

    sourceSets {
        // configure shared test folder
        val sharedTestFolder = "src/sharedTest/kotlin"
        val sharedTestResourcesFolder = "src/sharedTest/resources"
        named("test").configure {
            java.srcDirs(sharedTestFolder)
            resources.srcDirs(sharedTestResourcesFolder)
        }
        named("androidTest").configure {
            java.srcDirs(sharedTestFolder)
            resources.srcDirs(sharedTestResourcesFolder)
        }
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

    androidTestImplementation(libs.junit)
    androidTestImplementation(projects.kaspresso)
    androidTestImplementation(projects.composeSupport)
    androidTestImplementation(libs.androidXTestRunner)
    androidTestImplementation(libs.androidXTestRules)
    androidTestImplementation(libs.androidXTestExtJunitKtx)
    androidTestImplementation(libs.androidXTestExtJunit)
    androidTestImplementation(libs.composeUiTestJunit)

    testImplementation(libs.junit)
    testImplementation(projects.kaspresso)
    testImplementation(projects.composeSupport)
    testImplementation(libs.androidXTestRunner)
    testImplementation(libs.androidXTestRules)
    testImplementation(libs.androidXTestExtJunitKtx)
    testImplementation(libs.androidXTestExtJunit)
    testImplementation(libs.composeUiTestJunit)
    testImplementation(libs.robolectric)

    debugImplementation(libs.androidXTestFragmentTesting)

    androidTestUtil(libs.androidXTestOrchestrator)
}

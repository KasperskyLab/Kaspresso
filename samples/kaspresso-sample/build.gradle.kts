plugins {
    id("convention.android-app")
    id("com.kaspersky.kaspresso")
}

android {
    defaultConfig {
        applicationId = "com.kaspersky.kaspressample"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments["clearPackageData"] = "true"
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
    implementation(libs.multidex)

    androidTestImplementation(libs.junit)
    androidTestImplementation("com.kaspersky.android-components:kaspresso")
    androidTestImplementation(libs.androidXTestRunner)
    androidTestImplementation(libs.androidXTestRules)
    androidTestImplementation(libs.androidXTestExtJunitKtx)
    androidTestImplementation(libs.androidXTestExtJunit)

    testImplementation(libs.junit)
    testImplementation("com.kaspersky.android-components:kaspresso")
    testImplementation(libs.androidXTestRunner)
    testImplementation(libs.androidXTestRules)
    testImplementation(libs.androidXTestExtJunitKtx)
    testImplementation(libs.androidXTestExtJunit)
    testImplementation(libs.robolectric)

    debugImplementation(libs.androidXTestFragmentTesting)

    androidTestUtil(libs.androidXTestOrchestrator)
}

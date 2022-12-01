plugins {
    id("convention.android-app")
    id("com.kaspersky.kaspresso")
}

android {
    defaultConfig {
        minSdk = 21

        applicationId = "com.kaspersky.kaspresso.composesupport.sample"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments["clearPackageData"] = "true"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
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
    implementation(libs.bundles.compose)
    implementation(libs.lifecycleViewModelKtx)
    implementation(libs.lifecycleLiveDataKtx)
    implementation(libs.composeNavigation)
    implementation(libs.lifecycleViewModelComposeKtx)
    implementation(libs.composeRuntimeLiveData)

    androidTestImplementation("com.kaspersky.android-components:kaspresso")
    androidTestImplementation("com.kaspersky.android-components:kaspresso-compose-support")
    androidTestImplementation(libs.androidXTestRunner)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.composeUiTestJunit)

    testImplementation("com.kaspersky.android-components:kaspresso")
    testImplementation("com.kaspersky.android-components:kaspresso-compose-support")
    testImplementation(libs.androidXTestRunner)
    testImplementation(libs.junit)
    testImplementation(libs.composeUiTestJunit)
    testImplementation(libs.robolectric)

    androidTestUtil(libs.androidXTestOrchestrator)
}

plugins {
    id("convention.android-app")
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

    // kaspresso
    if (hasProperty("kaspresso.snapshotVersion")) {
        val kaspressoVersion = property("kaspresso.snapshotVersion")
        testImplementation("com.kaspersky.android-components:kaspresso:$kaspressoVersion")
        testImplementation("com.kaspersky.android-components:kaspresso-compose-support:$kaspressoVersion")
        androidTestImplementation("com.kaspersky.android-components:kaspresso:$kaspressoVersion")
        androidTestImplementation("com.kaspersky.android-components:kaspresso-compose-support:$kaspressoVersion")
    } else {
        testImplementation(projects.kaspresso)
        testImplementation(projects.composeSupport)
        androidTestImplementation(projects.kaspresso)
        androidTestImplementation(projects.composeSupport)
    }

    androidTestImplementation(libs.androidXTestRunner)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.composeUiTestJunit)

    testImplementation(libs.androidXTestRunner)
    testImplementation(libs.junit)
    testImplementation(libs.composeUiTestJunit)
    testImplementation(libs.robolectric)

    androidTestUtil(libs.androidXTestOrchestrator)
}

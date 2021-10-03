plugins {
    id("convention.android-app")
}

android {
    defaultConfig {
        applicationId = "com.kaspersky.kaspressample"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments["clearPackageData"] = "true"
    }

    sourceSets {
        val main by getting {
            java.srcDir("src/main/kotlin")
        }

        //configure shared test folder
        val sharedTestFolder = "src/sharedTest/kotlin"
        val androidTest by getting {
            java.srcDirs("src/andoroidTest/kotlin", sharedTestFolder )
        }
        val test by getting {
            java.srcDirs("src/test/java", sharedTestFolder )
        }
    }

    testOptions {
        execution = "ANDROIDX_TEST_ORCHESTRATOR"

        //allow to run tests on JVM
        unitTests.isReturnDefaultValues = true
        unitTests.isIncludeAndroidResources = true
    }

    //This is necessary to use activityScenarioRule in tests
    compileOptions{
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraint)

    androidTestImplementation(libs.runner)
    androidTestImplementation(libs.junit)
    androidTestImplementation(projects.kaspresso)
    androidTestImplementation(libs.androidXRules)
    androidTestImplementation(libs.androidXTestKtx)
    androidTestImplementation(libs.androidXTest)

    androidTestUtil(libs.orchestrator)

    testImplementation(libs.runner)
    testImplementation(libs.junit)
    testImplementation(projects.kaspresso)
    testImplementation(libs.androidXRules)
    testImplementation(libs.androidXTestKtx)
    testImplementation(libs.androidXTest)
    testImplementation(libs.robolectric)

    debugImplementation(libs.fragmentTesting)

}

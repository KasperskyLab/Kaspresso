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

        //configure shared test folder
        val sharedTestFolder = "src/sharedTest/kotlin"

        val androidTest by getting {
            java.srcDirs("src/andoroidTest/java", sharedTestFolder )
        }

        val test by getting {
            java.srcDirs("src/test/java", sharedTestFolder )
        }
    }

    testOptions {
        execution = "ANDROIDX_TEST_ORCHESTRATOR"

        //enable robolectric
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
    implementation(Dependencies.appcompat)
    implementation(Dependencies.material)
    implementation(Dependencies.constraint)

    androidTestImplementation(Dependencies.androidx_test)
    androidTestImplementation(Dependencies.androidx_test_ktx)
    androidTestImplementation(Dependencies.runner)
    androidTestImplementation(Dependencies.rules)
    androidTestImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.espressoCore)
    androidTestImplementation(Dependencies.espressoWeb)
    androidTestImplementation(Dependencies.kakao)
    androidTestImplementation(project(Projects.Kaspresso.framework))
    androidTestImplementation(project(Projects.Kautomator.framework))

    androidTestUtil(Dependencies.orchestrator)

    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.truth)

    //required for kaspresso-nitrogen
    testImplementation(Dependencies.androidx_test)
    testImplementation(Dependencies.androidx_test_ktx)
    testImplementation(Dependencies.rules)
    testImplementation(project(Projects.Kaspresso.framework))
    testImplementation(Dependencies.kakao)
    testImplementation(Dependencies.roboelectric)
    testImplementation(Dependencies.espressoCore)
    testImplementation(Dependencies.espressoWeb)

    debugImplementation(Dependencies.fragment_testing)
}

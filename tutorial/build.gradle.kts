plugins {
    id("convention.android-app")
}

android {
    defaultConfig {
        // remove after upgrading to 1.4
        minSdk = 21

        applicationId = "com.kaspersky.kaspresso.tutorial"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments["clearPackageData"] = "true"
    }

    testOptions {
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("com.google.android.material:material:1.6.1")

    androidTestImplementation("com.kaspersky.android-components:kaspresso:1.4.1")
    androidTestImplementation("androidx.test.ext:junit-ktx:1.1.3")
}

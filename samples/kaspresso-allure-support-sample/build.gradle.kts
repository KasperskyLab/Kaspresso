plugins {
    id("convention.android-app")
    id("com.kaspersky.kaspresso-adb-server-plugin")
}

android {
    namespace = "com.kaspersky.kaspresso.alluresupport.sample"
    defaultConfig {
        applicationId = "com.kaspersky.kaspresso.alluresupport.sample"
        testInstrumentationRunner = "com.kaspersky.kaspresso.runner.KaspressoRunner"
        testInstrumentationRunnerArguments["clearPackageData"] = "true"
    }

    testOptions {
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraint)
    implementation(libs.multidex)

    // kaspresso
    if (hasProperty("kaspresso.snapshotVersion")) {
        val kaspressoVersion = property("kaspresso.snapshotVersion")
        androidTestImplementation("com.kaspersky.android-components:kaspresso:$kaspressoVersion")
        androidTestImplementation("com.kaspersky.android-components:kaspresso-allure-support:$kaspressoVersion")
    } else {
        androidTestImplementation("com.kaspersky.android-components:kaspresso")
        androidTestImplementation("com.kaspersky.android-components:kaspresso-allure-support")
    }

    androidTestImplementation(libs.androidXTestExtJunitKtx)
    androidTestImplementation(libs.androidXTestExtJunit)
    androidTestImplementation(libs.junit)

    androidTestUtil(libs.androidXTestOrchestrator)
}

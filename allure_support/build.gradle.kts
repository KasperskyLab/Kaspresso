plugins {
    id("convention.android-library")
}

dependencies {
    implementation(projects.kaspresso)

    implementation(libs.kotlinStdlib)
    implementation(libs.truth)
    implementation(libs.testCore)
    implementation(libs.uiAutomator)
    implementation(libs.runner)

    implementation(libs.bundles.allure)

    testImplementation(libs.junit)

    androidTestImplementation(libs.espressoCore)
}

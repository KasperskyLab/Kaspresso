plugins {
    id("convention.android-library")
    id("convention.publication-android-lib")
}

publish {
    artifactId.set("kaspresso-allure-support")
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

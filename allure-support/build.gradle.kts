plugins {
    id("convention.android-library")
    id("convention.publication-android-lib")
    id("convention.third-party-report")
}

// TODO: move to publishing convention
android {
    libraryVariants.configureEach {
        packageLibraryProvider.configure {
            from("LICENSE.txt")
            from("NOTICE.txt")
        }
    }
}

publish {
    artifactId.set("kaspresso-allure-support")
}

dependencies {
    implementation(projects.kaspresso)

    implementation(libs.kotlinStdlib)
    implementation(libs.truth)
    implementation(libs.androidXTestCore)
    implementation(libs.androidXTestRunner)
    implementation(libs.uiAutomator)

    implementation(libs.bundles.allure)

    testImplementation(libs.junit)

    androidTestImplementation(libs.espressoCore)
}

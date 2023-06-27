plugins {
    id("convention.android-library")
    id("convention.publication-android-lib")
    id("convention.third-party-report")
    id("convention.legal-documents")
}

// TODO: move to publishing convention
android {
    libraryVariants.configureEach {
        packageLibraryProvider.configure {
            from("$rootDir/LICENSE.txt")
            from("NOTICE.txt")
        }
    }
}

publish {
    artifactId.set("kaspresso-allure-support")
}

dependencies {
    api(libs.bundles.allure)

    implementation(projects.kaspresso)

    implementation(libs.kotlinStdlib)
    implementation(libs.truth)
    implementation(libs.androidXTestCore)
    implementation(libs.androidXTestRunner)
    implementation(libs.uiAutomator)
    implementation(libs.androidXTestExtJunitKtx)

    testImplementation(libs.junit)

    androidTestImplementation(libs.espressoCore)
}

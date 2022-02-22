plugins {
    id("convention.android-library")
    id("convention.publication-android-lib")
}

android {
    libraryVariants.configureEach {
        packageLibraryProvider.configure {
            from("legal_docs/LICENSE.txt")
            from("legal_docs/NOTICE.txt")
        }
    }
}

publish {
    artifactId.set("kaspresso")
}

dependencies {
    api(projects.kautomator)
    api(libs.kakao)
    api(libs.bundles.espresso)
    api(libs.uiAutomator)
    api(libs.androidXCore)
    api(libs.androidXTestRules)

    implementation(libs.kotlinStdlib)
    implementation(libs.gson)
    implementation(projects.adbServer.adbserverDevice)

    testImplementation(libs.junit)
    testImplementation(libs.truth)
}

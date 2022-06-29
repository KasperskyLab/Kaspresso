plugins {
    id("convention.android-library")
    id("convention.publication-android-lib")
}

// TODO: move to publishing convention
android {
    libraryVariants.configureEach {
        packageLibraryProvider.configure {
            from("$rootDir/LICENSE.txt")
            from("$rootDir/NOTICE.txt")
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
    implementation(libs.coordinatorLayout)
    implementation(libs.material)

    testImplementation(libs.junit)
    testImplementation(libs.truth)
}

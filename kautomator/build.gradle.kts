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
    artifactId.set("kautomator")
}

dependencies {
    implementation(libs.kotlinStdlib)
    implementation(libs.truth)
    implementation(libs.androidXTestCore)
    implementation(libs.uiAutomator)
}

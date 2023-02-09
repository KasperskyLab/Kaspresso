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
    artifactId.set("kaspresso-compose-support")
}

dependencies {
    api(libs.kakaoCompose)
    api(libs.composeUiTest)

    implementation(projects.kaspresso)
    implementation(libs.kotlinStdlib)
}

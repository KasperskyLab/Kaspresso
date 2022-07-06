plugins {
    id("convention.android-library")
    id("convention.publication-android-lib")
    id("convention.third-party-report")
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

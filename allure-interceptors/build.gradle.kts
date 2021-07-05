plugins {
    id("convention.android-library")
    id("convention.publication-android-lib")
}

publish {
    artifactId.set("kaspresso-allure-interceptors")
}

dependencies {
    compileOnly(projects.kaspresso)
    implementation(libs.bundles.allure)
}

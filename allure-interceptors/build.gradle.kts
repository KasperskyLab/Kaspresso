plugins {
    id("convention.android-library")
}

dependencies {
    compileOnly(projects.kaspresso)
    api(libs.bundles.allure)
}

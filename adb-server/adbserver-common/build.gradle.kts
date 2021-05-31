plugins {
    id("convention.kotlin")
}

dependencies {
    implementation(libs.kotlinStdlib)
    testImplementation(libs.junitJupiter)
    testImplementation(libs.truth)
}

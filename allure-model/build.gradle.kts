plugins {
    id("convention.kotlin-library")
}

dependencies {
    compileOnly(libs.kotlinStdlib)
    compileOnly(libs.gson)
}

plugins {
    kotlin
}

dependencies {
    implementation(libs.kotlinStdlib)
    implementation(projects.adbServer.adbserverCommon)
    implementation(projects.adbServer.adbserverCommandTypes)
}

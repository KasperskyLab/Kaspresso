plugins {
    id("convention.kotlin-library")
    id("convention.publication-kotlin-lib")
}

publish {
    artifactId.set("adb-server-desktop-device-connection")
}

dependencies {
    implementation(libs.kotlinStdlib)
    implementation(projects.adbServer.adbserverCommon)
    implementation(projects.adbServer.adbserverCommandTypes)
}

plugins {
    id("convention.kotlin-library")
    id("convention.publication-kotlin-lib")
}
publish {
    artifactId.set("adb-server-device")
}

dependencies {
    implementation(libs.kotlinStdlib)
    api(projects.adbServer.adbserverCommon)
    api(projects.adbServer.adbserverCommandTypes)
    api(projects.adbServer.adbserverConnection)
    api(projects.adbServer.adbserverDesktopDeviceConnection)
}

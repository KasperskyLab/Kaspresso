plugins {
    id("convention.kotlin-library")
    id("convention.publication-kotlin-lib")
    id("convention.third-party-report")
    id("convention.legal-documents")
}

publish {
    artifactId.set("adb-server-device")
}

dependencies {
    implementation(libs.kotlinStdlib)
    api(projects.adbServer.adbServerCommon)
    api(projects.adbServer.adbServerCommandTypes)
    api(projects.adbServer.adbServerConnection)
    api(projects.adbServer.adbServerDesktopDeviceConnection)
}

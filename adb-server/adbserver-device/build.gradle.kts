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
    api(projects.adbServer.adbserverCommon)
    api(projects.adbServer.adbserverCommandTypes)
    api(projects.adbServer.adbserverConnection)
    api(projects.adbServer.adbserverDesktopDeviceConnection)
}

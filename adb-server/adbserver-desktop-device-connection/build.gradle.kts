plugins {
    id("convention.kotlin-library")
    id("convention.publication-kotlin-lib")
    id("convention.third-party-report")
    id("convention.legal-documents")
}

publish {
    artifactId.set("adb-server-desktop-device-connection")
}

dependencies {
    implementation(libs.kotlinStdlib)
    implementation(projects.adbServer.adbserverCommon)
    implementation(projects.adbServer.adbserverCommandTypes)
}

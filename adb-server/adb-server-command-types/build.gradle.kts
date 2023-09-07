plugins {
    id("convention.kotlin-library")
    id("convention.publication-kotlin-lib")
    id("convention.third-party-report")
    id("convention.legal-documents")
}

publish {
    artifactId.set("adb-server-command-types")
}

dependencies {
    implementation(libs.kotlinStdlib)
    implementation(projects.adbServer.adbServerCommon)
}

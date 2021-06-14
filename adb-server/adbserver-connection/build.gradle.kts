plugins {
    id("convention.kotlin-library")
    id("convention.publication-kotlin-lib")
}

publish {
    artifactId.set("adb-server-connection")
}

dependencies {
    implementation(libs.kotlinStdlib)
    implementation(projects.adbServer.adbserverCommon)
}

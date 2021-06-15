plugins {
    id("convention.kotlin-library")
    id("convention.publication-kotlin-lib")
}

publish {
    artifactId.set("adb-server-command-types")
}

dependencies {
    implementation(libs.kotlinStdlib)
    implementation(projects.adbServer.adbserverCommon)
}

plugins {
    id("convention.kotlin")
    id("convention.publication-kotlin-lib")
}

publish {
    artifactId.set("adb-server-common")
}

dependencies {
    implementation(libs.kotlinStdlib)
    testImplementation(libs.junitJupiter)
    testImplementation(libs.truth)
}

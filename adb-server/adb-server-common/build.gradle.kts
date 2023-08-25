plugins {
    id("convention.kotlin-library")
    id("convention.publication-kotlin-lib")
    id("convention.third-party-report")
    id("convention.legal-documents")
}

publish {
    artifactId.set("adb-server-common")
}

dependencies {
    implementation(libs.kotlinStdlib)
    testImplementation(libs.junitJupiter)
    testImplementation(libs.truth)
}

plugins {
    kotlin
}

dependencies {
    implementation(Dependencies.kotlinStdlib)

    testImplementation(Dependencies.junitJupiter)
    testImplementation(Dependencies.truth)
}
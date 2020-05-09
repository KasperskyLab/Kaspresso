plugins {
    kotlin
}

dependencies {
    implementation(Dependencies.kotlinStdlib)
    implementation(project(Projects.AdbServer.common))
    implementation(project(Projects.AdbServer.commandTypes))
}
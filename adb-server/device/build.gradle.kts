plugins {
    kotlin
}

dependencies {
    implementation(Dependencies.kotlinStdlib)
    implementation(project(Projects.AdbServer.common))
    implementation(project(Projects.AdbServer.commandTypes))
    implementation(project(Projects.AdbServer.connection))
    implementation(project(Projects.AdbServer.desktopDeviceConnection))
}
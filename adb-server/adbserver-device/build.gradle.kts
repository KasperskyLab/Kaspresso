import org.gradle.jvm.tasks.Jar

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

tasks {
    val jar by getting(Jar::class) {
        from(configurations.runtimeClasspath.get().map {
            if (it.isDirectory) it else zipTree(it)
        })
    }
}
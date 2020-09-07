import org.gradle.jvm.tasks.Jar

plugins {
    kotlin
    application
}

dependencies {
    implementation(Dependencies.kotlinStdlib)
    implementation(Dependencies.kotlinCli)
    implementation(project(Projects.AdbServer.common))
    implementation(project(Projects.AdbServer.commandTypes))
    implementation(project(Projects.AdbServer.connection))
    implementation(project(Projects.AdbServer.desktopDeviceConnection))
}

setProperty("mainClassName", "com.kaspersky.adbserver.desktop.MainKt")

tasks {
    val jar by getting(Jar::class) {
        manifest {
            attributes["Main-Class"] = "com.kaspersky.adbserver.desktop.MainKt"
        }
        from(configurations.runtimeClasspath.get().map {
            if (it.isDirectory) it else zipTree(it)
        })
    }
}
import org.gradle.jvm.tasks.Jar

plugins {
    kotlin
    application
}

dependencies {
    implementation(Dependencies.kotlinStdlib)
    implementation(project(Projects.AdbServer.common))
    implementation(project(Projects.AdbServer.commandTypes))
    implementation(project(Projects.AdbServer.connection))
    implementation(project(Projects.AdbServer.desktopDeviceConnection))
}

setProperty("mainClassName", "com.kaspersky.adbserver.MainKt")

tasks {
    val jar by getting(Jar::class) {
        manifest {
            attributes["Main-Class"] = "com.kaspersky.adbserver.MainKt"
        }
        from(configurations.runtimeClasspath.get().map {
            if (it.isDirectory) it else zipTree(it)
        })
    }
}
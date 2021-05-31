import org.gradle.jvm.tasks.Jar

plugins {
    id("convention.kotlin-library")
    id("convention.publication-kotlin-lib")
}
publish {
    artifactId.set("adb-server-device")
}

dependencies {
    implementation(libs.kotlinStdlib)
    api(projects.adbServer.adbserverCommon)
    api(projects.adbServer.adbserverCommandTypes)
    api(projects.adbServer.adbserverConnection)
    api(projects.adbServer.adbserverDesktopDeviceConnection)
}

tasks.jar.configure {
    val included = listOf(
        "adbserver-common",
        "adbserver-command-types",
        "adbserver-connection",
        "adbserver-desktop-device-connection"
    )

    archiveBaseName.set(project.name)
    archiveVersion.set("")

    from(
        configurations.runtimeClasspath.get()
            .filter { included.contains(it.nameWithoutExtension) }
            .map { if (it.isDirectory) it else zipTree(it) }
    )
}

import org.gradle.jvm.tasks.Jar

plugins {
    kotlin
    javaLibrary
}

dependencies {
    implementation(libs.kotlinStdlib)
    implementation(projects.adbServer.adbserverCommon)
    implementation(projects.adbServer.adbserverCommandTypes)
    implementation(projects.adbServer.adbserverConnection)
    implementation(projects.adbServer.adbserverDesktopDeviceConnection)
}

task(name = "sourcesJar", type = Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.getByName("main").allSource)
}

task(name = "javadocJar", type = Jar::class) {
    archiveClassifier.set("javadoc")
    from(tasks.javadoc.get().destinationDir)
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

plugins {
    id("convention.kotlin-app")
    id("convention.third-party-report")
}

dependencies {
    implementation(libs.kotlinStdlib)
    implementation(libs.kotlinCli)
    implementation(projects.adbServer.adbServerCommon)
    implementation(projects.adbServer.adbServerCommandTypes)
    implementation(projects.adbServer.adbServerConnection)
    implementation(projects.adbServer.adbServerDesktopDeviceConnection)
}

setProperty("mainClassName", "com.kaspersky.adbserver.desktop.MainKt")

tasks.withType<Jar>().configureEach {
    manifest {
        attributes["Main-Class"] = "com.kaspersky.adbserver.desktop.MainKt"
    }
    from(configurations.runtimeClasspath.get().map {
        if (it.isDirectory) it else zipTree(it)
    }) {
        exclude("META-INF/**/**/module-info.class")
    }
    exclude("NOTICE.txt")
    exclude("LICENSE.txt")

    mustRunAfter(
        ":adb-server:adb-server-desktop-device-connection:jar",
        ":adb-server:adb-server-command-types:jar",
        ":adb-server:adb-server-connection:jar",
        ":adb-server:adb-server-common:jar"
    )
}

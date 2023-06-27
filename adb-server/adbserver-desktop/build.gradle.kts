plugins {
    id("convention.kotlin-app")
    id("convention.third-party-report")
    id("convention.legal-documents")
}

dependencies {
    implementation(libs.kotlinStdlib)
    implementation(libs.kotlinCli)
    implementation(projects.adbServer.adbserverCommon)
    implementation(projects.adbServer.adbserverCommandTypes)
    implementation(projects.adbServer.adbserverConnection)
    implementation(projects.adbServer.adbserverDesktopDeviceConnection)
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
}

plugins {
    id("convention.kotlin-library")
    id("convention.publication-kotlin-lib")
    id("convention.third-party-report")
}

publish {
    artifactId.set("adb-server-desktop-device-connection")
}

sourceSets {
    main {
        resources {
            srcDirs("$rootDir/adb-server/license/")
        }
    }
}

dependencies {
    implementation(libs.kotlinStdlib)
    implementation(projects.adbServer.adbserverCommon)
    implementation(projects.adbServer.adbserverCommandTypes)
}

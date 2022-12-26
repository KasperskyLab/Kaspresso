plugins {
    id("convention.kotlin-library")
    id("convention.publication-kotlin-lib")
    id("convention.third-party-report")
}

publish {
    artifactId.set("adb-server-connection")
}

sourceSets {
    main {
        resources {
            srcDirs("$rootDir/adb-server/adbserver-connection/license/")
        }
    }
}

dependencies {
    implementation(libs.kotlinStdlib)
    implementation(projects.adbServer.adbserverCommon)
}

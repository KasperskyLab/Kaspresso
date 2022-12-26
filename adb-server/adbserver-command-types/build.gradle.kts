plugins {
    id("convention.kotlin-library")
    id("convention.publication-kotlin-lib")
    id("convention.third-party-report")
}

publish {
    artifactId.set("adb-server-command-types")
}

sourceSets {
    main {
        resources {
            srcDirs("$rootDir/adb-server/adbserver-command-types/license/")
        }
    }
}

dependencies {
    implementation(libs.kotlinStdlib)
    implementation(projects.adbServer.adbserverCommon)
}

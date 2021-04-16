import Dependencies.Versions

plugins {
    androidLibrary
    kotlinAndroid
}

android {
    compileSdkVersion(Versions.compileSdk)
    buildToolsVersion(Versions.buildTools)

    defaultConfig {
        minSdkVersion(Versions.minSdk)
    }

    sourceSets {
        val main by getting {
            java.srcDirs("src/main/kotlin", "src/main/java")
        }
    }
}

dependencies {
    api(fileTree("libs").matching { include("*.jar") })

    implementation(libs.kotlinStdlib)
    implementation(libs.uiAutomator)
    implementation(libs.androidXCore)
    implementation(libs.androidXRules)
    implementation(libs.kakao)
    implementation(libs.gson)
    implementation(libs.bundles.espresso)

    implementation(projects.kautomator)
    implementation(projects.adbServer.adbserverDevice) { isTransitive = false }

    testImplementation(libs.junit)
    testImplementation(libs.truth)
}

tasks {
    val sourcesJar by registering(Jar::class) {
        archiveClassifier.set("sources")
        from(android.sourceSets.getByName("main").java.srcDirs)
    }

    val javadocJar by registering(Jar::class) {
        archiveClassifier.set("javadoc")
        from(dokkaJavadoc.get().outputDirectory.get())
    }
}

artifacts {
    archives(tasks.getByName("sourcesJar"))
    archives(tasks.getByName("javadocJar"))
}

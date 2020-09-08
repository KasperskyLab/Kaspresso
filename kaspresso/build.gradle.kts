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
    api(fileTree("libs").matching{ include("*.jar") })

    implementation(Dependencies.kotlinStdlib)
    implementation(Dependencies.rules)
    implementation(Dependencies.espressoCore)
    implementation(Dependencies.espressoWeb)
    implementation(Dependencies.uiAutomator)
    implementation(Dependencies.kakao)
    implementation(Dependencies.gson)
    implementation(Dependencies.androidXCore)
    implementation(project(Projects.Kautomator.framework))
    implementation(project(Projects.AdbServer.device))
    implementation(project(Projects.AdbServer.common))

    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.truth)
}

tasks {
    val sourcesJar by registering(Jar::class) {
        archiveClassifier.set("sources")
        from(android.sourceSets.getByName("main").java.srcDirs)
    }

    val javadocJar by registering(Jar::class) {
        dependsOn(dokkaJavadoc)
        archiveClassifier.set("javadoc")
        from(dokkaJavadoc.get().outputDirectory.get())
    }
}

artifacts {
    archives(tasks.getByName("sourcesJar"))
    archives(tasks.getByName("javadocJar"))
}

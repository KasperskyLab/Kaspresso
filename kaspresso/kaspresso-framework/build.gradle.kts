import Dependencies.Versions
import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    androidLibrary
    kotlinAndroid
    dokka
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

    implementation(Dependencies.rules)
    implementation(Dependencies.espressoCore)
    implementation(Dependencies.espressoWeb)
    implementation(Dependencies.uiAutomator)
    implementation(Dependencies.kakao)
    implementation(Dependencies.gson)
    implementation(Dependencies.androidXCore)
    implementation(project(Projects.Kautomator.framework))
    implementation(project(Projects.AdbServer.device))

    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.truth)
}

tasks {
    val dokka by getting(DokkaTask::class) {
        outputFormat = "gfm"
        outputDirectory = "$rootDir/docs"

        configuration {
            reportUndocumented = true
        }
    }

    val sourcesJar by registering(Jar::class) {
        archiveClassifier.set("sources")
        from(android.sourceSets.getByName("main").java.srcDirs)
    }

    val javadocJar by registering(Jar::class) {
        dependsOn(dokka)
        archiveClassifier.set("javadoc")
        from(dokka.outputDirectory)
    }
}

artifacts {
    archives(tasks.getByName("sourcesJar"))
    archives(tasks.getByName("javadocJar"))
}

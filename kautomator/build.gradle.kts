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
    implementation(Dependencies.kotlinStdlib)
    implementation(Dependencies.truth)
    implementation(Dependencies.testCore)
    implementation(Dependencies.uiAutomator)
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

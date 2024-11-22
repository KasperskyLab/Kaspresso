rootProject.name = "kaspresso-framework"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    val kasperskyRepoUrl: String? by settings
    repositories {
        gradlePluginPortal()
        google()
        kasperskyRepoUrl?.let { maven { setUrl(it) } }
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.4.0"
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        mavenCentral()
        mavenLocal()
        google()
        maven { url = uri("https://kotlin.bintray.com/kotlinx") }
        maven { url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots") }
    }
}

includeBuild("build-logic")

include(
    ":adb-server:adb-server-command-types",
    ":adb-server:adb-server-common",
    ":adb-server:adb-server-connection",
    ":adb-server:adbserver-desktop",
    ":adb-server:adb-server-desktop-device-connection",
    ":adb-server:adb-server-device",

    ":page-object-code-gen",

    ":kaspresso",
    ":kautomator",
    ":allure-support",
    ":compose-support",

    ":samples:adbserver-sample",
    ":samples:kaspresso-sample",
    ":samples:kautomator-sample",
    ":samples:kautomator-sample-app-upgrade",
    ":samples:kaspresso-allure-support-sample",
    ":samples:kaspresso-compose-support-sample",

    ":tutorial",
)

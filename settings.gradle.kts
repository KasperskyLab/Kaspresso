rootProject.name = "kaspresso-framework"

enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    val kasperskyRepoUrl: String? by settings
    repositories {
        gradlePluginPortal()
        google()
        kasperskyRepoUrl?.let { maven { setUrl(it) } }
    }
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
    ":adb-server:adbserver-command-types",
    ":adb-server:adbserver-common",
    ":adb-server:adbserver-connection",
    ":adb-server:adbserver-desktop",
    ":adb-server:adbserver-desktop-device-connection",
    ":adb-server:adbserver-device",

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

    ":tutorial"
)

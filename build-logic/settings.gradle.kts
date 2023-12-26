enableFeaturePreview("VERSION_CATALOGS")

rootProject.name = "build-logic"

include("android")
include("checks")
include("kotlin")
include("publication")
include("third-party-report")

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }

    val kasperskyRepoUrl: String? by settings
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        kasperskyRepoUrl?.let { maven { setUrl(it) } }
    }
}

enableFeaturePreview("VERSION_CATALOGS")

rootProject.name = "build-logic"

include("android")
include("checks")
include("kotlin")
include("publication")

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

    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

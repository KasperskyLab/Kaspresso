rootProject.name = "build-logic"

include("android")
include("checks")
include("kotlin")
include("publication")
include("third-party-report")

pluginManagement {
    val kasperskyRepoUrl: String? by settings
    val kasperskyMirrorRepoUrl: String? by settings
    val kasperskyLibsRepoUrl: String? by settings
    repositories {
        kasperskyRepoUrl?.let { maven { setUrl(it) } }
        kasperskyMirrorRepoUrl?.let { maven { setUrl(it) } }
        kasperskyLibsRepoUrl?.let { maven { setUrl(it) } }
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
    val kasperskyMirrorRepoUrl: String? by settings
    val kasperskyLibsRepoUrl: String? by settings
    repositories {
        kasperskyRepoUrl?.let { maven { setUrl(it) } }
        kasperskyMirrorRepoUrl?.let { maven { setUrl(it) } }
        kasperskyLibsRepoUrl?.let { maven { setUrl(it) } }
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

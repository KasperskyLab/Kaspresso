import com.android.build.gradle.LibraryExtension
import com.kaspersky.kaspresso.publication.KotlinLibraryPublishExtension

plugins {
    id("com.android.library")
    id("convention.publication-base")
}

val publishExtension = extensions.create<KotlinLibraryPublishExtension>("publish")
publishExtension.artifactId.convention(project.name)

configure<LibraryExtension> {
    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

publishing {
    publications {
        register<MavenPublication>("release") {
            afterEvaluate {
                from(components["release"])
                artifactId = publishExtension.artifactId.get()
            }
        }
    }
}

android {
    libraryVariants.configureEach {
        packageLibraryProvider.configure {
            from("$rootDir/LICENSE.txt")
            from("NOTICE.txt")
        }
    }
}
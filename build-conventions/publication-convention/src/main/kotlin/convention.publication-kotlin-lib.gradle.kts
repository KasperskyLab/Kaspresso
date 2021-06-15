import com.kaspersky.kaspresso.publication.KotlinLibraryPublishExtension

plugins {
    id("convention.publication-release")
}

plugins.withId("kotlin") {

    @Suppress("UnstableApiUsage")
    extensions.getByType<JavaPluginExtension>().run {
        withSourcesJar()
        withJavadocJar()
    }
}

val publishExtension = extensions.create<KotlinLibraryPublishExtension>("publish")

publishing {
    publications {
        register<MavenPublication>("kotlinLibraryMaven") {
            from(components["java"])

            afterEvaluate {
                artifactId = publishExtension.artifactId.getOrElse(project.name)
            }
        }
    }
}

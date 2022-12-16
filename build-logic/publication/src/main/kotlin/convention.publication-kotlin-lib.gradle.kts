import com.kaspersky.kaspresso.publication.KotlinLibraryPublishExtension

plugins {
    id("convention.publication-base")
}

plugins.withId("kotlin") {
    extensions.getByType<JavaPluginExtension>().run {
        withSourcesJar()
        withJavadocJar()
    }
}

val publishExtension = extensions.create<KotlinLibraryPublishExtension>("publish")
publishExtension.artifactId.convention(project.name)

publishing {
    publications {
        register<MavenPublication>("kotlinLibraryMaven") {
            from(components["java"])

            afterEvaluate {
                artifactId = publishExtension.artifactId.get()
            }
        }
    }
}

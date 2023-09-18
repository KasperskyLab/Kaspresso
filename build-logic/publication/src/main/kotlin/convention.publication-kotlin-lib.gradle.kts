import com.kaspersky.kaspresso.publication.KotlinLibraryPublishExtension

plugins {
    id("convention.publication-base")
    `java-library`
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

tasks.withType<Jar>().configureEach {
    from(projectDir) {
        include("NOTICE.txt")
    }
    from(rootDir) {
        include("LICENSE.txt")
    }
}

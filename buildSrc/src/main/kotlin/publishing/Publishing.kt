package publishing

import org.gradle.api.Project
import org.gradle.api.publish.PublicationContainer
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication

private const val SOURCES_TASK = "sourcesJar"
private const val JAVADOC_TASK = "javadocJar"

private const val NODE_DEPENDENCIES = "dependencies"
private const val NODE_DEPENDENCY = "dependency"
private const val DEPENDENCIES_CONFIGURATION = "implementation"
private const val UNSPECIFIED_DEPENDENCY = "unspecified"

private const val DEPENDENCY_GROUP = "groupId"
private const val DEPENDENCY_ARTIFACT = "artifactId"
private const val DEPENDENCY_VERSION = "version"
private const val DEPENDENCY_KAUTOMATOR = "kautomator"
private const val DEPENDENCY_KAUTOMATOR_SHORT = "kautomator"

private const val PROPERTY_VERSION = "stableVersion"
private const val PROPERTY_VERSION_SNAPSHOT = "snapshotVersion"
private const val PROPERTY_GROUP_ID = "publish.artifactGroup"
private const val PROPERTY_ARTIFACT_NAME = "publish.artifactName"
private const val PROPERTY_PUBLICATION_NAME = "publish.publicationName"

val Project.shouldBePublished get() = listOf(
    "kaspresso",
    "kautomator",
    "adbserver-device"
).contains(name)

fun PublishingExtension.setup(project: Project) {
    publications {
        createWithNameAndVersion(
            project = project,
            publicationName = project.findProperty(PROPERTY_PUBLICATION_NAME).toString(),
            publicationVersion = project.findProject(PROPERTY_VERSION).toString()
        )

        createWithNameAndVersion(
            project = project,
            publicationName = "${project.findProperty(PROPERTY_PUBLICATION_NAME)}Snapshot",
            publicationVersion = project.findProperty(PROPERTY_VERSION_SNAPSHOT).toString()
        )
    }
}

private fun PublicationContainer.createWithNameAndVersion(
    project: Project,
    publicationName: String,
    publicationVersion: String
): MavenPublication {
    return create(publicationName, MavenPublication::class.java) {
        project.afterEvaluate {
            artifact(file("$buildDir/outputs/aar/$name-release.aar"))
            artifact(tasks.getByName(SOURCES_TASK))
            artifact(tasks.getByName(JAVADOC_TASK))
        }

        artifactId = project.findProperty(PROPERTY_ARTIFACT_NAME).toString()
        groupId = project.findProperty(PROPERTY_GROUP_ID).toString()
        version = publicationVersion

        pom {
            withXml {
                val root = asNode()
                val dependenciesNode = root.appendNode(NODE_DEPENDENCIES)

                project.configurations.getByName(DEPENDENCIES_CONFIGURATION).allDependencies.forEach {
                    if (it.name != UNSPECIFIED_DEPENDENCY && it.version != null) {
                        val dependencyNode = dependenciesNode.appendNode(NODE_DEPENDENCY)

                        if (it.name != DEPENDENCY_KAUTOMATOR) { // TODO: Move to project settings
                            dependencyNode.appendNode(DEPENDENCY_GROUP, it.group)
                            dependencyNode.appendNode(DEPENDENCY_VERSION, it.version)
                            dependencyNode.appendNode(DEPENDENCY_ARTIFACT, it.name)
                        } else {
                            dependencyNode.appendNode(DEPENDENCY_GROUP, groupId)
                            dependencyNode.appendNode(DEPENDENCY_VERSION, publicationVersion)
                            dependencyNode.appendNode(DEPENDENCY_ARTIFACT, DEPENDENCY_KAUTOMATOR_SHORT)
                        }
                    }
                }
            }
        }
    }
}

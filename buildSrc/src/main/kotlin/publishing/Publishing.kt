package publishing

import org.gradle.api.Project
import org.gradle.api.plugins.JavaLibraryPlugin
import org.gradle.api.publish.PublicationContainer
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication

private const val SOURCES_TASK = "sourcesJar"
private const val JAVADOC_TASK = "javadocJar"

private const val NODE_DEPENDENCIES = "dependencies"
private const val NODE_DEPENDENCY = "dependency"
private const val DEPENDENCIES_CONFIGURATION = "implementation"
private const val UNSPECIFIED = "unspecified"

private const val DEPENDENCY_GROUP = "groupId"
private const val DEPENDENCY_ARTIFACT = "artifactId"
private const val DEPENDENCY_VERSION = "version"
private const val DEPENDENCY_KAUTOMATOR = "kautomator"
private const val DEPENDENCY_ADB_SERVER = "adbserver-device"

private const val PROPERTY_VERSION = "stableVersion"
private const val PROPERTY_VERSION_SNAPSHOT = "snapshotVersion"
private const val PROPERTY_GROUP_ID = "publish.artifactGroup"
private const val PROPERTY_ARTIFACT_NAME = "publish.artifactName"
private const val PROPERTY_PUBLICATION_NAME = "publish.publicationName"

val Project.shouldBePublished
    get() = listOf(
        "kaspresso",
        "kautomator",
        "adbserver-device"
    ).contains(name)

fun PublishingExtension.setup(project: Project) {
    publications {
        createWithNameAndVersion(
            project = project,
            publicationName = project.findProperty(PROPERTY_PUBLICATION_NAME).toString(),
            publicationVersion = project.findProperty(PROPERTY_VERSION).toString()
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
            if (project.plugins.hasPlugin(JavaLibraryPlugin::class.java)) {
                artifact(file("$buildDir/libs/$name.jar"))
            }
            if (project.plugins.hasPlugin("com.android.library"))
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

                    val dependencyNode = dependenciesNode.appendNode(NODE_DEPENDENCY)

                    fun appendArtifact(group: String?, version: String?, artifact: String?) {
                        dependencyNode.appendNode(DEPENDENCY_GROUP, group)
                        dependencyNode.appendNode(DEPENDENCY_ARTIFACT, artifact)
                        dependencyNode.appendNode(DEPENDENCY_VERSION, version)
                    }

                    when {
                        listOf(DEPENDENCY_KAUTOMATOR, DEPENDENCY_ADB_SERVER).contains(it.name) -> {
                            appendArtifact(groupId, publicationVersion, it.name)
                        }
                        it.version != UNSPECIFIED && it.version != null -> {
                            appendArtifact(it.group, it.version, it.name)
                        }
                        else -> {
                            dependenciesNode.remove(dependencyNode)
                        }
                    }
                }
            }
        }
    }
}

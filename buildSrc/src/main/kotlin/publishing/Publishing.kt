package publishing

import org.gradle.api.Project
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

private const val PROPERTY_VERSION = "stableVersion"
private const val PROPERTY_GROUP_ID = "publish.artifactGroup"

fun PublishingExtension.setup(project: Project) {

    publications {
        create(project.name, MavenPublication::class.java) {
            project.afterEvaluate {
                artifact(file("$buildDir/outputs/aar/$name-release.aar"))
                artifact(tasks.getByName(SOURCES_TASK))
                artifact(tasks.getByName(JAVADOC_TASK))
            }

            artifactId = project.name
            groupId = project.findProperty(PROPERTY_GROUP_ID) as? String
            version = project.findProperty(PROPERTY_VERSION) as? String

            pom {
                withXml {
                    val root = asNode()
                    val dependenciesNode = root.appendNode(NODE_DEPENDENCIES)

                    project.configurations.getByName(DEPENDENCIES_CONFIGURATION).allDependencies.forEach {
                        if (it.name != UNSPECIFIED_DEPENDENCY && it.version != null) {
                            val dependencyNode = dependenciesNode.appendNode(NODE_DEPENDENCY)
                            dependencyNode.appendNode(DEPENDENCY_GROUP, it.group)
                            dependencyNode.appendNode(DEPENDENCY_ARTIFACT, it.name)
                            dependencyNode.appendNode(DEPENDENCY_VERSION, it.version)
                        }
                    }
                }
            }
        }
    }
}
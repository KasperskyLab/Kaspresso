import com.android.build.gradle.internal.tasks.factory.dependsOn
import groovy.lang.GroovyObject
import org.jfrog.gradle.plugin.artifactory.dsl.PublisherConfig
import publishing.setup
import publishing.shouldBePublished
import java.util.Date

buildscript {

    repositories {
        jcenter()
        google()
        maven { url = uri("https://plugins.gradle.org/m2/") }
    }

    dependencies {
        classpath("com.android.tools.build:gradle:4.1.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:1.4.0")
        classpath("com.jfrog.bintray.gradle:gradle-bintray-plugin:1.8.5")
        classpath("org.jfrog.buildinfo:build-info-extractor-gradle:4.17.2")
    }
}

plugins {
    detekt
    mavenPublish
    bintray
    artifactory
    dokka
}

subprojects {
    apply(plugin = Dependencies.Detect.plugin)
    apply(plugin = Dependencies.Dokka.plugin)

    dependencies {
        detekt(Dependencies.Detect.cli)
        detekt(Dependencies.Detect.formatting)
    }

    tasks.dokkaGfm.configure {
        val parentDir = when {
            parent != rootProject && parent != null -> "docs" + File.separator + parent?.name
            else -> "docs"
        }
        outputDirectory.set(rootDir.resolve(parentDir))
    }

    detekt {
        failFast = true
        autoCorrect = true
        config = files("${rootDir}/static-analysis/config/detekt/config.yml")

        reports {
            html {
                enabled = true
                destination = file("${rootDir}/static-analysis/reports/detekt_${project.name}.html")
            }
        }
    }

    if (shouldBePublished) {
        apply(plugin = Dependencies.Publishing.mavenPlugin)
        apply(plugin = Dependencies.Publishing.bintrayPlugin)
        apply(plugin = Dependencies.Publishing.artifactoryPlugin)

        publishing {
            setup(this@subprojects)
        }

        val publicationName = findProperty("publish.publicationName").toString()

        bintray {
            user = findProperty("bintrayuser").toString()
            key = findProperty("bintraykey").toString()
            setPublications(publicationName)

            pkg.apply {
                repo = findProperty("publish.bintrayRepo").toString()
                name = publicationName
                userOrg = user
                vcsUrl = "https://github.com/KasperskyLab/Kaspresso.git"
                setLicenses("Apache-2.0")

                version.apply {
                    name = findProperty("stableVersion").toString()
                    released = Date().toString()
                }
            }

            version = findProperty("stableVersion").toString()
        }

        artifactory {
            publish(delegateClosureOf<PublisherConfig> {
                setContextUrl("https://oss.jfrog.org/artifactory")

                repository(delegateClosureOf<GroovyObject> {
                    setProperty("repoKey", "oss-snapshot-local")
                    setProperty("username", findProperty("bintrayuser").toString())
                    setProperty("password", findProperty("artifactoryPassword").toString())
                    setProperty("maven", true)
                })

                defaults(delegateClosureOf<GroovyObject> {
                    invokeMethod("publications", "${publicationName}Snapshot")
                    setProperty("publishArtifacts", true)
                    setProperty("publishPom", true)
                })
            })

            resolve(delegateClosureOf<GroovyObject> {
                setProperty("repoKey", "libs-snapshot")
                setProperty("username", findProperty("bintrayuser").toString())
                setProperty("password", findProperty("artifactoryPassword").toString())
                setProperty("maven", true)
            })
        }

        tasks.artifactoryPublish.dependsOn("assemble")
        tasks.bintrayUpload.dependsOn("assemble")
    }
}

allprojects {
    repositories {
        jcenter()
        google()
        maven { url = uri("https://kotlin.bintray.com/kotlinx") }
    }
}

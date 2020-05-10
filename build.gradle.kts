import groovy.lang.GroovyObject
import org.jfrog.gradle.plugin.artifactory.dsl.PublisherConfig
import publishing.setup
import publishing.shouldBePublished

buildscript {

    repositories {
        jcenter()
        google()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }

    dependencies {
        classpath(Dependencies.Classpath.gradlePlugin)
        classpath(Dependencies.Classpath.kotlinGradlePlugin)
        classpath(Dependencies.Classpath.dokkaPlugin)
        classpath(Dependencies.Classpath.bintrayPlugin)
        classpath(Dependencies.Classpath.artifactoryPlugin)
    }
}

plugins {
    detekt
    mavenPublish
    bintray
    artifactory
}

subprojects {
    apply(plugin = Dependencies.Detect.plugin)

    dependencies {
        detekt(Dependencies.Detect.cli)
        detekt(Dependencies.Detect.formatting)
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
                repo = "Kaspresso"
                name = publicationName
                userOrg = user
                vcsUrl = "https://github.com/KasperskyLab/Kaspresso.git"
                setLicenses("Apache-2.0")
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

        tasks.named("artifactoryPublish") {
            dependsOn("assemble")
        }
    }
}

allprojects {
    repositories {
        jcenter()
        google()
    }
}
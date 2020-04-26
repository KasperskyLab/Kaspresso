import groovy.xml.QName
import publishing.setup

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

    if (name == "kaspresso" || name == "kautomator") {
        apply(plugin = "maven-publish")
        apply(plugin = "com.jfrog.bintray")

        publishing {
            setup(this@subprojects)
        }

        bintray {
            user = findProperty("bintrayuser").toString()
            key = findProperty("bintraykey").toString()
            setPublications(name)

            pkg.apply {
                repo = "Kaspresso"
                name = "Kaspresso"
                userOrg = user
                vcsUrl = "https://github.com/KasperskyLab/Kaspresso.git"
                setLicenses("Apache-2.0")
            }

            version = findProperty("stableVersion").toString()
        }
    }
}

allprojects {
    repositories {
        jcenter()
        google()
    }
}
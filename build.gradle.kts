buildscript {

    repositories {
        jcenter()
        google()
    }

    dependencies {
        classpath(Dependencies.Classpath.gradlePlugin)
        classpath(Dependencies.Classpath.kotlinGradlePlugin)
        classpath(Dependencies.Classpath.dokkaPlugin)
        classpath(Dependencies.Classpath.bintrayPlugin)
    }
}

plugins {
    detekt
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
}


allprojects {
    repositories {
        jcenter()
        google()
    }
}
import org.gradle.kotlin.dsl.`java-library`
import org.gradle.kotlin.dsl.`maven-publish`
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.version
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

val PluginDependenciesSpec.androidLibrary: PluginDependencySpec
        get() = id("com.android.library")

val PluginDependenciesSpec.androidApplication: PluginDependencySpec
        get() = id("com.android.application")

val PluginDependenciesSpec.kotlinAndroid: PluginDependencySpec
        get() = kotlin("android")

val PluginDependenciesSpec.kotlinAndroidExtensions: PluginDependencySpec
        get() = kotlin("android.extensions")

val PluginDependenciesSpec.detekt: PluginDependencySpec
        get() = id("io.gitlab.arturbosch.detekt") version "1.16.0"

val PluginDependenciesSpec.dokka: PluginDependencySpec
        get() = id("org.jetbrains.dokka") version "1.4.0"

val PluginDependenciesSpec.javaLibrary: PluginDependencySpec
        get() = `java-library`

val PluginDependenciesSpec.kotlin: PluginDependencySpec
        get() = kotlin("jvm")

val PluginDependenciesSpec.bintray: PluginDependencySpec
        get() = id("com.jfrog.bintray") version "1.8.5"

val PluginDependenciesSpec.mavenPublish: PluginDependencySpec
        get() = `maven-publish`

val PluginDependenciesSpec.artifactory: PluginDependencySpec
        get() = id("com.jfrog.artifactory") version "4.17.2"

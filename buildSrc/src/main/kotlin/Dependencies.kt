object Dependencies {

    object Versions {
        const val targetSdk = 29
        const val compileSdk = 29
        const val minSdk = 18
        const val buildTools = "29.0.3"

        const val kotlin = "1.3.72"

        const val appcompat = "1.1.0"
        const val androidXCore = "1.2.0"
        const val material = "1.1.0"
        const val constraint = "1.1.3"

        const val runner = "1.2.0"
        const val junit = "4.12"
        const val testCore = "1.2.0"
        const val espresso = "3.2.0"
        const val kakao = "2.3.2"
        const val uiAutomator = "2.2.0"
        const val adbServer = "1.0.1"

        const val truth = "1.0"
        const val gson = "2.8.6"

        const val detekt = "1.8.0"
        const val dokka = "0.10.1"

        const val bintray = "1.8.4"
        const val artifactory = "4.15.2"
    }

    object Detect {
        const val plugin = "io.gitlab.arturbosch.detekt"
        const val cli = "io.gitlab.arturbosch.detekt:detekt-cli:${Versions.detekt}"
        const val formatting = "io.gitlab.arturbosch.detekt:detekt-formatting:${Versions.detekt}"
    }

    object Classpath {
        const val gradlePlugin = "com.android.tools.build:gradle:3.6.3"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val dokkaPlugin = "org.jetbrains.dokka:dokka-gradle-plugin:${Versions.dokka}"
        const val bintrayPlugin = "com.jfrog.bintray.gradle:gradle-bintray-plugin:${Versions.bintray}"
        const val artifactoryPlugin = "org.jfrog.buildinfo:build-info-extractor-gradle:${Versions.artifactory}"
    }

    object Publishing {
        const val mavenPlugin = "maven-publish"
        const val bintrayPlugin = "com.jfrog.bintray"
        const val artifactoryPlugin = "com.jfrog.artifactory"
    }

    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib"

    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val androidXCore = "androidx.core:core:${Versions.androidXCore}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"

    const val testCore = "androidx.test:core:${Versions.testCore}"
    const val runner = "androidx.test:runner:${Versions.runner}"
    const val rules = "androidx.test:rules:${Versions.runner}"

    const val junit = "junit:junit:${Versions.junit}"
    const val junitJupiter = "org.junit.jupiter:junit-jupiter:5.6.2"
    const val truth = "com.google.truth:truth:${Versions.truth}"

    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val espressoWeb = "androidx.test.espresso:espresso-web:${Versions.espresso}"
    const val kakao = "com.agoda.kakao:kakao:${Versions.kakao}"

    const val uiAutomator = "androidx.test.uiautomator:uiautomator:${Versions.uiAutomator}"
    const val adbServer = "com.kaspersky.android-components:adbserver-device:${Versions.adbServer}"
}

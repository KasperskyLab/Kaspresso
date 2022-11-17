package com.kaspersky.kaspresso.publication

import org.gradle.api.provider.Property

@Suppress("UnnecessaryAbstractClass")
abstract class KotlinLibraryPublishExtension {

    /**
     * non blank value will modify artifact id of maven coordinates
     *
     * default: project.name
     */
    abstract val artifactId: Property<String>
}

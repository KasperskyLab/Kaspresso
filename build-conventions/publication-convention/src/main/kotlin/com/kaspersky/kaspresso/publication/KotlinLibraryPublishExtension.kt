package com.kaspersky.kaspresso.publication

import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import org.gradle.kotlin.dsl.property
import javax.inject.Inject

abstract class KotlinLibraryPublishExtension @Inject constructor(objects: ObjectFactory) {

    /**
     * non blank value will modify artifact id of maven coordinates
     *
     * default: project.name
     */
    val artifactId: Property<String> = objects.property()
}

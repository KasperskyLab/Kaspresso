package com.kaspersky.kaspresso.annotations

/**
 * Special annotation to indicate that this type is supported only for a given API level and below
 */
@Target(
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.FIELD
)
@Retention(AnnotationRetention.BINARY)
annotation class MaximumSupportedVersion(

    /**
     * This sets the target api level for the type
     */
    val value: Int
)

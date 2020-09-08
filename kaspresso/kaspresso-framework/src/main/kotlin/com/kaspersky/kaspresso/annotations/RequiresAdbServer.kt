package com.kaspersky.kaspresso.annotations

/**
 * Special annotation to mark methods or classes that require AdbServer to be started for execution
 *
 * Required: Started AdbServer
 *     1. Download a file "kaspresso/artifacts/adbserver-desktop.jar"
 *     2. Start AdbServer => input in cmd "java -jar path_to_file/adbserver-desktop.jar"
 */

@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class RequiresAdbServer

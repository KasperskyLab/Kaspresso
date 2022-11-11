package com.kaspersky.kaspresso.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class KaspressoPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        println("Hello from kaspresso plugin")
    }
}

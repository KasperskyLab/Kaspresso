package com.kaspersky.kaspresso.plugin

import com.android.build.gradle.BasePlugin
import com.android.build.gradle.TestedExtension
import com.android.build.gradle.api.TestVariant
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.TaskProvider
import org.gradle.kotlin.dsl.withType

class KaspressoPlugin : Plugin<Project> {
    private lateinit var project: Project

    private lateinit var startServerTaskProvider: TaskProvider<Task>
    private lateinit var stopServerTaskProvider: TaskProvider<Task>

    override fun apply(target: Project) {
        project = target

        // wait applying any android plugin
        project.plugins.withType<BasePlugin> {
            init()
        }
    }

    private fun init() {
        val android = project.extensions.getByName("android") as TestedExtension

        stopServerTaskProvider = project.tasks.register(STOP_ADB_SERVER_TASK_NAME) {
            doLast { println("Stop adb server") }
        }

        startServerTaskProvider = project.tasks.register(START_ADB_SERVER_TASK_NAME) {
            doLast { println("Start adb server") }
            finalizedBy(stopServerTaskProvider)
        }

        android.testVariants.all(this::configureVariant)
    }

    private fun configureVariant(testVariant: TestVariant) {
        val connectedTestProvider = testVariant.connectedInstrumentTestProvider
        connectedTestProvider.configure {
            dependsOn(startServerTaskProvider)
            finalizedBy(stopServerTaskProvider)
        }
    }


    companion object {
        const val START_ADB_SERVER_TASK_NAME = "kaspressoStartAdbServer"
        const val STOP_ADB_SERVER_TASK_NAME = "kaspressoStopAdbServer"
    }
}

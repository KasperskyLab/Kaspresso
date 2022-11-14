package com.kaspersky.kaspresso.plugin

import com.android.build.gradle.BasePlugin
import com.android.build.gradle.TestedExtension
import com.android.build.gradle.api.TestVariant
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.TaskProvider
import org.gradle.internal.os.OperatingSystem
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.withType

class KaspressoPlugin : Plugin<Project> {
    private lateinit var project: Project

    private lateinit var extension: KaspressoExtension
    private lateinit var startServerTaskProvider: TaskProvider<Task>
    private lateinit var stopServerTaskProvider: TaskProvider<Task>
    private val desktopServerHolder = DesktopServerHolder()

    override fun apply(target: Project) {
        project = target

        extension = project.extensions.create(EXTENSION_NAME)
        extension.workingDirectory.convention(project.rootProject.projectDir.toPath())

        // wait applying any android plugin
        project.plugins.withType<BasePlugin> {
            init()
        }
    }

    private fun init() {
        val android = project.extensions.getByName("android") as TestedExtension
        extension.adbPath.convention(project.provider {
            val sdkDir = android.sdkDirectory.toPath()
            val adbName = if (OperatingSystem.current() == OperatingSystem.WINDOWS) "adb.exe" else "adb"
            sdkDir.resolve("platform-tools").resolve(adbName)
        })

        stopServerTaskProvider = project.tasks.register(STOP_ADB_SERVER_TASK_NAME) {
            doLast { desktopServerHolder.stop() }
        }

        startServerTaskProvider = project.tasks.register(START_ADB_SERVER_TASK_NAME) {
            doLast {
                desktopServerHolder.start(
                    workingDirectory = extension.workingDirectory.get(),
                    adbPath = extension.adbPath.get()
                )
            }
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
        const val EXTENSION_NAME = "kaspresso"
        const val START_ADB_SERVER_TASK_NAME = "kaspressoStartAdbServer"
        const val STOP_ADB_SERVER_TASK_NAME = "kaspressoStopAdbServer"
    }
}

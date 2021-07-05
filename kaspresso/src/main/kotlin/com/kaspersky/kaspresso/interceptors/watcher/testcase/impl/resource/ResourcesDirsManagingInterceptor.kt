package com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.resource

import com.kaspersky.kaspresso.files.resources.ResourcesDirsProvider
import com.kaspersky.kaspresso.interceptors.watcher.testcase.TestRunWatcherInterceptor
import com.kaspersky.kaspresso.testcases.models.info.TestInfo

class ResourcesDirsManagingInterceptor(
    private val resourcesDirsProvider: ResourcesDirsProvider
) : TestRunWatcherInterceptor {

    override fun onTestStarted(testInfo: TestInfo): Unit = resourcesDirsProvider.onNewTestRun()
}

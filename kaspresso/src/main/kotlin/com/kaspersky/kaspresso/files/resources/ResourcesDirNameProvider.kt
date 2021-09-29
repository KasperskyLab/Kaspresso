package com.kaspersky.kaspresso.files.resources

import com.kaspersky.kaspresso.files.models.TestMethod

/**
 * Provides directory names for resources
 */
interface ResourcesDirNameProvider {

    fun getResourcesDirName(testMethod: TestMethod, runNumber: Int): String
}

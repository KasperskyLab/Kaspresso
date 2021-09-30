package com.kaspersky.kaspresso.files.resources

import com.kaspersky.kaspresso.files.models.TestMethod

/**
 * Provides directory names for resources
 */
interface ResourcesDirNameProvider {

    fun provideResourcesDirName(testMethod: TestMethod): String
}

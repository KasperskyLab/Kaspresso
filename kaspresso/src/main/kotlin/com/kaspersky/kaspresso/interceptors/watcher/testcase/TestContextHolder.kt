package com.kaspersky.kaspresso.interceptors.watcher.testcase

import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext

/**
 * Holder of BaseTestContext
 */
interface TestContextHolder {

    fun setBaseTestContext(context: BaseTestContext) = Unit
}

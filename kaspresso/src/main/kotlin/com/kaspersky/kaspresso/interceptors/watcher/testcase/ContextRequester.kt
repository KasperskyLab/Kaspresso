package com.kaspersky.kaspresso.interceptors.watcher.testcase

import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext

interface ContextRequester {

    fun requestBaseTestContest(context: BaseTestContext) = Unit
}
package com.kaspersky.kaspressonitrogen.interceptors.watcher.testcase

import com.kaspersky.kaspressonitrogen.testcases.core.testcontext.NitrogenBaseTestContext

interface NitrogenTestContextHolder {

    fun setBaseTestContext(context: NitrogenBaseTestContext) = Unit
}

package com.kaspersky.components.alluresupport.results

import java.util.concurrent.atomic.AtomicBoolean

// TODO(Nikita Evdokimov) - Certainly there should be a better way
/**
 * @see com.kaspersky.components.alluresupport.interceptors.testrun.VisualTestLateFailInterceptor
 */
object AllureVisualTestFlag {
    val shouldFailLate = AtomicBoolean(false)
}

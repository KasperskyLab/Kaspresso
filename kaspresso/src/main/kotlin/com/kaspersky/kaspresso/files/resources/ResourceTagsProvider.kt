package com.kaspersky.kaspresso.files.resources

import com.kaspersky.kaspresso.testcases.models.info.StepInfo

interface ResourceTagsProvider<T> {
    fun provide(source: T): String
}

class DefaultScreenshotTagsProvider : ResourceTagsProvider<StepInfo> {
    override fun provide(source: StepInfo): String = "${source.testClassName}_step_${source.ordinal}"
}

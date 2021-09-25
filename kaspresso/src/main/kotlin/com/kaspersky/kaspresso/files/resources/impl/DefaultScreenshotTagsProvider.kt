package com.kaspersky.kaspresso.files.resources.impl

import com.kaspersky.kaspresso.files.resources.ResourceTagsProvider
import com.kaspersky.kaspresso.testcases.models.info.StepInfo

class DefaultScreenshotTagsProvider : ResourceTagsProvider<StepInfo> {
    override fun provide(source: StepInfo): String = "${source.testClassName}_step_${source.ordinal}"
}

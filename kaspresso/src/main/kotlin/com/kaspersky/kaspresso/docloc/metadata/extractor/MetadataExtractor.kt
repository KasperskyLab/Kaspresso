package com.kaspersky.kaspresso.docloc.metadata.extractor

import com.kaspersky.kaspresso.docloc.metadata.Metadata

internal interface MetadataExtractor {
    fun getMetadata(): Metadata
}

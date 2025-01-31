package com.kaspersky.kaspresso.docloc.metadata.extractor

import com.kaspersky.kaspresso.docloc.metadata.LocalizedString

internal class MetadataExtractorHelper {
    fun resolveAmbiguous(localizedStrings: List<LocalizedString>): List<LocalizedString> {
        return localizedStrings.groupBy { it.locValueDescription }
            .values
            .flatMap { groupedById ->
                if (groupedById.size == 1) groupedById else addIndexes(
                    groupedById
                )
            }
    }

    private fun addIndexes(groupedById: List<LocalizedString>): List<LocalizedString> {
        return groupedById.mapIndexed { index, locString ->
            locString.copy(locValueDescription = "${locString.locValueDescription}$INDEX_SEPARATOR${index + 1}")
        }
    }

    companion object {
        private const val INDEX_SEPARATOR = '_'
    }
}

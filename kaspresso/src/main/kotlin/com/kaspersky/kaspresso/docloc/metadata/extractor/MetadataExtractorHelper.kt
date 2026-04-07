package com.kaspersky.kaspresso.docloc.metadata.extractor

import com.kaspersky.kaspresso.docloc.metadata.LocalizedString

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

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

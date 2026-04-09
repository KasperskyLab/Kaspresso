package com.kaspersky.kaspresso.internal.extensions.other

import android.text.TextUtils.htmlEncode
import com.kaspersky.kaspresso.docloc.metadata.LocalizedString
import com.kaspersky.kaspresso.docloc.metadata.Metadata

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

/**
 * Transforms [Metadata] object to an xml string.
 *
 * @param productPackage package of application.
 * @return serialized Metadata string.
 */
internal fun Metadata.toXml(productPackage: String): String {
    val locStringsXml = window.localizedStrings.joinToString(separator = "") { it.toXml(productPackage) }
    val windowXml = """<Window Left="${window.left}" Top="${window.top}" Width="${window.width}" Height="${window.height}">$locStringsXml</Window>"""
    return """<Metadata>$windowXml</Metadata>"""
}

/**
 * Transforms [LocalizedString] object to an xml string.
 *
 * @return serialized LocalizedString string.
 */
internal fun LocalizedString.toXml(locPrefix: String): String {
    return """<LocalizedString Text="${htmlEncode(text)}" LocValueDescription="$locPrefix:id/$locValueDescription" Top="$top" Left="$left" Width="$width" Height="$height"/>"""
}

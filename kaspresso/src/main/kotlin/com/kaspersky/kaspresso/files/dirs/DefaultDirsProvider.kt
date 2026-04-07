package com.kaspersky.kaspresso.files.dirs

import android.annotation.SuppressLint
import android.os.Build
import android.os.Environment
import com.kaspersky.kaspresso.instrumental.InstrumentalDependencyProvider
import com.kaspersky.kaspresso.internal.extensions.other.createDirIfNeeded
import java.io.File

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

class DefaultDirsProvider(
    private val instrumentationDependencyProvider: InstrumentalDependencyProvider
) : DirsProvider {

    private val clearedDirs = HashSet<File>()

    @SuppressLint("WorldReadableFiles", "ObsoleteSdkInt")
    override fun provideNew(dest: File): File {
        val dir = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).resolve(dest)
        } else {
            Environment.getExternalStorageDirectory().resolve(dest)
        }

        return dir.createDirIfNeeded()
    }

    override fun provideCleared(dest: File): File {
        if (!clearedDirs.contains(dest)) {
            clearDir(dest, inclusive = false)
            clearedDirs.add(dest)
        }
        return dest.createDirIfNeeded()
    }

    @Suppress("SameParameterValue")
    private fun clearDir(dest: File, inclusive: Boolean) {
        clearDirManually(dest, inclusive)
        if (dest.exists() && shouldClearDirThroughShell(dest)) {
            clearDirThroughShell(dest, inclusive)
        }
    }

    private fun shouldClearDirThroughShell(dest: File): Boolean {
        val device = instrumentationDependencyProvider.uiDevice
        return device.executeShellCommand("ls ${dest.absolutePath}").isNotEmpty()
    }

    private fun clearDirThroughShell(dest: File, inclusive: Boolean) {
        val device = instrumentationDependencyProvider.uiDevice
        if (inclusive) {
            device.executeShellCommand("rm -r ${dest.absolutePath}")
        } else {
            device.executeShellCommand("find ${dest.absolutePath} -type f -delete")
        }
    }

    private fun clearDirManually(path: File, inclusive: Boolean) {
        if (path.isDirectory) {
            path.listFiles()?.forEach { clearDirManually(path = it, inclusive = true) }
        } else {
            path.delete()
        }
        if (inclusive) {
            path.delete()
        }
    }
}

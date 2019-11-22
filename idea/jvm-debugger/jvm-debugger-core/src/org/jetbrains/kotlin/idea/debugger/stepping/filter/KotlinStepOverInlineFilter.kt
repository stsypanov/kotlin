/*
 * Copyright 2010-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.idea.debugger.stepping.filter

import com.intellij.debugger.engine.DebugProcessImpl
import com.intellij.debugger.engine.SuspendContextImpl
import com.intellij.openapi.project.Project
import com.intellij.util.Range
import com.sun.jdi.Location
import org.jetbrains.kotlin.idea.debugger.safeLineNumber
import org.jetbrains.kotlin.idea.debugger.safeSourceName
import org.jetbrains.kotlin.idea.debugger.stepping.KotlinMethodFilter

class KotlinStepOverInlineFilter(
    val project: Project,
    private val linesToSkip: Set<Int>,
    private val methodLines: IntRange,
    private val fileName: String
) : KotlinMethodFilter {
    override fun locationMatches(context: SuspendContextImpl, location: Location): Boolean {
        return locationMatches(context.debugProcess, location)
    }

    override fun locationMatches(process: DebugProcessImpl, location: Location): Boolean {
        val lineNumber = location.safeLineNumber()

        if (location.safeSourceName() == fileName) {
            return lineNumber !in linesToSkip
        } else {
            return true
        }
    }

    override fun getCallingExpressionLines(): Range<Int>? = null
}
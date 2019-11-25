/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.backend.jvm.lower

import org.jetbrains.kotlin.backend.common.FileLoweringPass
import org.jetbrains.kotlin.backend.common.phaser.makeIrFilePhase
import org.jetbrains.kotlin.backend.jvm.JvmBackendContext
import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.declarations.IrFile
import org.jetbrains.kotlin.ir.expressions.IrMemberAccessExpression
import org.jetbrains.kotlin.ir.expressions.IrTypeOperator
import org.jetbrains.kotlin.ir.expressions.IrTypeOperatorCall
import org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid
import org.jetbrains.kotlin.ir.visitors.acceptChildrenVoid

val jvmArgumentNullabilityAssertions =
    makeIrFilePhase<JvmBackendContext>(
        { JvmArgumentNullabilityAssertionsLowering() },
        name = "Nullability assertions on arguments",
        description = "Transform nullability assertions on arguments so that they conform to the compiler settings"
    )

class JvmArgumentNullabilityAssertionsLowering : FileLoweringPass, IrElementVisitorVoid {

    override fun lower(irFile: IrFile) {
        irFile.acceptChildrenVoid(this)
    }

    override fun visitElement(element: IrElement) {
        element.acceptChildrenVoid(this)
    }

    override fun visitMemberAccess(expression: IrMemberAccessExpression) {
        expression.acceptChildrenVoid(this)

        // Always drop nullability assertions on dispatch receivers, assuming that it will throw NPE.
        //
        // NB there are some members in Kotlin built-in classes which are NOT implemented as platform method calls,
        // and thus break this assertion - e.g., 'Array<T>.iterator()' and similar functions.
        // See KT-30908 for more details.
        val dispatchReceiver = expression.dispatchReceiver
        if (dispatchReceiver is IrTypeOperatorCall && dispatchReceiver.operator == IrTypeOperator.IMPLICIT_NOTNULL) {
            expression.dispatchReceiver = dispatchReceiver.argument
        }
    }
}
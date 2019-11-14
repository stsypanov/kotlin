/*
 * Copyright 2010-2018 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

@file:kotlin.jvm.JvmMultifileClass
@file:kotlin.jvm.JvmName("StringsKt")

package kotlin.text

/**
 * Clears the content of this string builder making it empty.
 *
 * @sample samples.text.Strings.clearStringBuilder
 */
@SinceKotlin("1.3")
public actual fun StringBuilder.clear(): StringBuilder = apply { setLength(0) }

/**
 * Sets the character at the specified [index] to the specified [value].
 *
 * @throws IndexOutOfBoundsException if [index] is out of bounds of this string builder.
 */
@kotlin.internal.InlineOnly
public actual inline operator fun StringBuilder.set(index: Int, value: Char): Unit = this.setCharAt(index, value)

/**
 * Replaces characters in the specified range of this string builder with characters in the specified [string].
 *
 * @param startIndex the beginning (inclusive) of the range to replace.
 * @param endIndex the end (exclusive) of the range to replace.
 * @param string the string to replace with.
 *
 * @throws IndexOutOfBoundsException or [IllegalArgumentException] if [startIndex] is less than zero, greater than the length of this string builder, or `startIndex > endIndex`.
 *
 * @return this string builder.
 */
@SinceKotlin("1.3")
@ExperimentalStdlibApi
@kotlin.internal.InlineOnly
public actual inline fun StringBuilder.setRange(startIndex: Int, endIndex: Int, string: String): StringBuilder =
    this.replace(startIndex, endIndex, string)

/**
 * Removes the character at the specified [index] from this string builder.
 *
 * If the `Char` at the specified [index] is part of a supplementary code point, this method does not remove the entire supplementary character.
 *
 * @param index the index of `Char` to remove.
 *
 * @throws IndexOutOfBoundsException if [index] is out of bounds of this string builder.
 *
 * @return this string builder.
 */
@SinceKotlin("1.3")
@ExperimentalStdlibApi
@kotlin.internal.InlineOnly
public actual inline fun StringBuilder.deleteAt(index: Int): StringBuilder = this.deleteCharAt(index)

/**
 * Removes characters in the specified range from this string builder.
 *
 * @param startIndex the beginning (inclusive) of the range to remove.
 * @param endIndex the end (exclusive) of the range to remove.
 *
 * @throws IndexOutOfBoundsException or [IllegalArgumentException] when [startIndex] or [endIndex] is out of range of this string builder indices or when `startIndex > endIndex`.
 *
 * @return this string builder.
 */
@SinceKotlin("1.3")
@ExperimentalStdlibApi
@kotlin.internal.InlineOnly
public actual inline fun StringBuilder.deleteRange(startIndex: Int, endIndex: Int): StringBuilder = this.delete(startIndex, endIndex)

/**
 * Copies characters from this string builder into the [destination] character array.
 *
 * @param destination the array to copy to.
 * @param destinationOffset the position in the array to copy to.
 * @param startIndex the beginning (inclusive) of the range to copy.
 * @param endIndex the end (exclusive) of the range to copy.
 *
 * @throws IndexOutOfBoundsException or [IllegalArgumentException] when [startIndex] or [endIndex] is out of range of this string builder indices or when `startIndex > endIndex`.
 * @throws IndexOutOfBoundsException when the subrange doesn't fit into the [destination] array starting at the specified [destinationOffset],
 *  or when that index is out of the [destination] array indices range.
 */
@SinceKotlin("1.3")
@ExperimentalStdlibApi
@kotlin.internal.InlineOnly
public actual inline fun StringBuilder.toCharArray(destination: CharArray, destinationOffset: Int, startIndex: Int, endIndex: Int) =
    this.getChars(startIndex, endIndex, destination, destinationOffset)

/**
 * Appends characters in a subarray of the specified [chars] array to this string builder.
 *
 * Characters are appended in order, starting at specified [startIndex].
 *
 * @param chars the array from which characters are appended.
 * @param startIndex the beginning (inclusive) of the subarray to append.
 * @param endIndex the end (exclusive) of the subarray to append.
 *
 * @throws IndexOutOfBoundsException or [IllegalArgumentException] when [startIndex] or [endIndex] is out of range of the [chars] array indices or when `startIndex > endIndex`.
 *
 * @return this string builder.
 */
@SinceKotlin("1.3")
@ExperimentalStdlibApi
@kotlin.internal.InlineOnly
public actual inline fun StringBuilder.appendRange(chars: CharArray, startIndex: Int, endIndex: Int): StringBuilder =
    this.append(chars, startIndex, endIndex - startIndex)

/**
 * Appends a subsequence of the specified character sequence [csq] to this string builder.
 *
 * @param csq the character sequence from which a subsequence is appended. If [csq] is `null`,
 *  then characters are appended as if [csq] contained the four characters `"null"`.
 * @param startIndex the beginning (inclusive) of the subsequence to append.
 * @param endIndex the end (exclusive) of the subsequence to append.
 *
 * @throws IndexOutOfBoundsException or [IllegalArgumentException] when [startIndex] or [endIndex] is out of range of the [csq] character sequence indices or when `startIndex > endIndex`.
 *
 * @return this string builder.
 */
@SinceKotlin("1.3")
@ExperimentalStdlibApi
@kotlin.internal.InlineOnly
public actual inline fun StringBuilder.appendRange(csq: CharSequence?, startIndex: Int, endIndex: Int): StringBuilder =
    this.append(csq, startIndex, endIndex)

/**
 * Inserts characters in a subarray of the specified [chars] array into this string builder at the specified [index].
 *
 * The inserted characters go in same order as in the [chars] array, starting at [index].
 *
 * @param index the position in this string builder to insert at.
 * @param chars the array from which characters are inserted.
 * @param startIndex the beginning (inclusive) of the subarray to insert.
 * @param endIndex the end (exclusive) of the subarray to insert.
 *
 * @throws IndexOutOfBoundsException or [IllegalArgumentException] when [startIndex] or [endIndex] is out of range of the [chars] array indices or when `startIndex > endIndex`.
 * @throws IndexOutOfBoundsException if [index] is less than zero or greater than the length of this string builder.
 *
 * @return this string builder.
 */
@SinceKotlin("1.3")
@ExperimentalStdlibApi
@kotlin.internal.InlineOnly
public actual inline fun StringBuilder.insertRange(index: Int, chars: CharArray, startIndex: Int, endIndex: Int): StringBuilder =
    this.insert(index, chars, startIndex, endIndex - startIndex)

/**
 * Inserts characters in a subsequence of the specified character sequence [csq] into this string builder at the specified [index].
 *
 * The inserted characters go in the same order as in the [csq] character sequence, starting at [index].
 *
 * @param index the position in this string builder to insert at.
 * @param csq the character sequence from which a subsequence is inserted. If [csq] is `null`,
 *  then characters will be inserted as if [csq] contained the four characters `"null"`.
 * @param startIndex the beginning (inclusive) of the subsequence to insert.
 * @param endIndex the end (exclusive) of the subsequence to insert.
 *
 * @throws IndexOutOfBoundsException or [IllegalArgumentException] when [startIndex] or [endIndex] is out of range of the [csq] character sequence indices or when `startIndex > endIndex`.
 * @throws IndexOutOfBoundsException if [index] is less than zero or greater than the length of this string builder.
 *
 * @return this string builder.
 */
@SinceKotlin("1.3")
@ExperimentalStdlibApi
@kotlin.internal.InlineOnly
public actual inline fun StringBuilder.insertRange(index: Int, csq: CharSequence?, startIndex: Int, endIndex: Int): StringBuilder =
    this.insert(index, csq, startIndex, endIndex)


private object SystemProperties {
    /** Line separator for current system. */
    @JvmField
    val LINE_SEPARATOR = System.getProperty("line.separator")!!
}

/** Appends a line separator to this Appendable. */
public fun Appendable.appendln(): Appendable = append(SystemProperties.LINE_SEPARATOR)

/** Appends value to the given Appendable and line separator after it. */
@kotlin.internal.InlineOnly
public inline fun Appendable.appendln(value: CharSequence?): Appendable = append(value).appendln()

/** Appends value to the given Appendable and line separator after it. */
@kotlin.internal.InlineOnly
public inline fun Appendable.appendln(value: Char): Appendable = append(value).appendln()

/** Appends a line separator to this StringBuilder. */
public fun StringBuilder.appendln(): StringBuilder = append(SystemProperties.LINE_SEPARATOR)

/** Appends [value] to this [StringBuilder], followed by a line separator. */
@kotlin.internal.InlineOnly
public inline fun StringBuilder.appendln(value: StringBuffer?): StringBuilder = append(value).appendln()

/** Appends [value] to this [StringBuilder], followed by a line separator. */
@kotlin.internal.InlineOnly
public inline fun StringBuilder.appendln(value: CharSequence?): StringBuilder = append(value).appendln()

/** Appends [value] to this [StringBuilder], followed by a line separator. */
@kotlin.internal.InlineOnly
public inline fun StringBuilder.appendln(value: String?): StringBuilder = append(value).appendln()

/** Appends [value] to this [StringBuilder], followed by a line separator. */
@kotlin.internal.InlineOnly
public inline fun StringBuilder.appendln(value: Any?): StringBuilder = append(value).appendln()

/** Appends [value] to this [StringBuilder], followed by a line separator. */
@kotlin.internal.InlineOnly
public inline fun StringBuilder.appendln(value: StringBuilder?): StringBuilder = append(value).appendln()

/** Appends [value] to this [StringBuilder], followed by a line separator. */
@kotlin.internal.InlineOnly
public inline fun StringBuilder.appendln(value: CharArray): StringBuilder = append(value).appendln()

/** Appends [value] to this [StringBuilder], followed by a line separator. */
@kotlin.internal.InlineOnly
public inline fun StringBuilder.appendln(value: Char): StringBuilder = append(value).appendln()

/** Appends [value] to this [StringBuilder], followed by a line separator. */
@kotlin.internal.InlineOnly
public inline fun StringBuilder.appendln(value: Boolean): StringBuilder = append(value).appendln()

/** Appends [value] to this [StringBuilder], followed by a line separator. */
@kotlin.internal.InlineOnly
public inline fun StringBuilder.appendln(value: Int): StringBuilder = append(value).appendln()

/** Appends [value] to this [StringBuilder], followed by a line separator. */
@kotlin.internal.InlineOnly
public inline fun StringBuilder.appendln(value: Short): StringBuilder = append(value.toInt()).appendln()

/** Appends [value] to this [StringBuilder], followed by a line separator. */
@kotlin.internal.InlineOnly
public inline fun StringBuilder.appendln(value: Byte): StringBuilder = append(value.toInt()).appendln()

/** Appends [value] to this [StringBuilder], followed by a line separator. */
@kotlin.internal.InlineOnly
public inline fun StringBuilder.appendln(value: Long): StringBuilder = append(value).appendln()

/** Appends [value] to this [StringBuilder], followed by a line separator. */
@kotlin.internal.InlineOnly
public inline fun StringBuilder.appendln(value: Float): StringBuilder = append(value).appendln()

/** Appends [value] to this [StringBuilder], followed by a line separator. */
@kotlin.internal.InlineOnly
public inline fun StringBuilder.appendln(value: Double): StringBuilder = append(value).appendln()

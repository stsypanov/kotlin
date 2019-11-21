// !LANGUAGE: +NewInference +SamConversionPerArgument
// !DIAGNOSTICS: -UNUSED_EXPRESSION

// FILE: JClass.java

public class JClass {
    public Number foo(Runnable r1, Runnable r2) { return null; }
    public String foo(Object r1, Object r2) { return null; }
}

// FILE: test.kt

fun test(j: JClass, r: Runnable) {
    val a0 = j.foo({}, {})
    <!DEBUG_INFO_EXPRESSION_TYPE("(kotlin.Number..kotlin.Number?)")!>a0<!>

    val a1 = j.foo({}, r)
    <!DEBUG_INFO_EXPRESSION_TYPE("(kotlin.String..kotlin.String?)")!>a1<!>

    val a2 = j.foo(r, {})
    <!DEBUG_INFO_EXPRESSION_TYPE("(kotlin.String..kotlin.String?)")!>a2<!>
}
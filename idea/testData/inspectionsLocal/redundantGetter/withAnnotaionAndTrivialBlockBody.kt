class Foo {
    val foo: String = ""
        @Deprecated("") <caret>get() {
            return field
        }
}
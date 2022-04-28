package ac.obl.cc.ctx

fun wrapper(impl: (() -> Unit) -> Unit): Wrapper = object : Wrapper() {
    override fun impl(it: () -> Unit) = impl(it)
}

abstract class Wrapper {
    abstract fun impl(it: () -> Unit)

    operator fun <R> invoke(body: () -> R): R {
        var result: R? = null
        impl { result = body() }
        @Suppress("UNCHECKED_CAST")
        return result as R
    }

    operator fun plus(other: Wrapper) = wrapper {
        impl {
            other.impl { it() }
        }
    }
}

// example

val logged = wrapper {
    println("before logged")
    try { it() } finally {
        println("after logged")
    }
}

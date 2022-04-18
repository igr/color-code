package ac.obl.cc.ctx

import ac.obl.cc.CONTEXT

/**
 * Sequential context.
 */
@CONTEXT
class Ctx<A>(private val value: A) {

    companion object {
        /**
         * Alt. constructor.
         */
        fun <V> of(value: V) = Ctx(value)

        operator fun <V> plus(value: V): Ctx<V> {
            return Ctx(value)
        }
    }

    // map

    /**
     * Basic transformation of the context.
     */
    fun <B> map(fn: (A) -> B): Ctx<B> {
        return Ctx(fn(value))
    }

    operator fun <B> plus(fn: (A) -> B): Ctx<B> {
        return Ctx(fn(value))
    }

    // consume

    /**
     * Consumes the value of this context.
     */
    fun use(consumer: (A) -> Unit): Ctx<A> {
        consumer(value)
        return this
    }

    operator fun minus(consumer: (A) -> Unit): Ctx<A> {
        consumer(value)
        return this
    }

    // return

    /**
     * Returns a provider function that returns the value of this context.
     */
    fun get(): () -> A {
        return { invoke() }
    }

    operator fun invoke(): A {
        return value
    }
}

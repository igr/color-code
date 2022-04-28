package ac.obl.cc.ctx

import ac.obl.cc.CONTEXT

/**
 * Context definition.
 */
@CONTEXT
class CtxD<A>(private val provider: () -> A)  {
    companion object {
        /**
         * Alt. constructor.
         */
        fun <V> of(value: V) = CtxD { value }

        operator fun <V> plus(value: V): CtxD<V> = CtxD { value }

        operator fun <V> plus(provider: () -> V): CtxD<V> = CtxD(provider)
    }

    // map

    /**
     * Basic transformation of the context.
     */
    fun <B> map(fn: (A) -> B): CtxD<B> {
        return CtxD { fn(provider()) }
    }

    operator fun <B> plus(fn: (A) -> B): CtxD<B> = map(fn)

    // consume

    /**
     * Consumes the value of this context.
     */
    fun use(consumer: (A) -> Unit): CtxD<A> {
        return CtxD {
            val v = provider()
            consumer(v)
            v
        }
    }

    operator fun minus(consumer: (A) -> Unit): CtxD<A> = use(consumer)

    // return

    /**
     * Returns a provider function that returns the value of this context.
     */
    fun get(): () -> A {
        return { invoke() }
    }

    operator fun invoke(): A {
        return provider()
    }

    // wrap

    fun wrap(wrapper: (() -> A) -> A): CtxD<A> {
        return CtxD {
            wrapper {
                invoke()
            }
        }
    }

    operator fun times(wrapper: (() -> A) -> A): CtxD<A> = wrap(wrapper)

}

package ac.obl.cc.calc

import ac.obl.cc.ctx.CtxD

fun main() {

    // The use of delayed context.
    //
    // ((4 + 8) / 3) + (6 / 2)

    (CtxD
        + (Value(4))
        + with(8)
        + add
        - printValue
        + with(3)
        + divide()
        + with(rightSide())
        + add
        - printValue
        )()
}

private val rightSide =
    (CtxD
        + (Value(6))
        + with(2)
        + divide()
        + { it.number }
        )

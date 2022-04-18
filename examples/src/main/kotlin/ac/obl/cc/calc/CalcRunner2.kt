package ac.obl.cc.calc

import ac.obl.cc.ctx.Ctx

fun main() {

    // A more complex calculation that requires the joining of two contexts.
    //
    // ((4 + 8) / 3) + (6 / 2)

    (Ctx
        + (Value(4))
        + with(8)
        + add
        + with(3)
        + divide()
        + with(rightSide())
        + add
        )
}

// Private function required by the need of joining two contexts.
private val rightSide =
    (Ctx
        + (Value(6))
        + with(2)
        + divide()
        + { it.number }
        )

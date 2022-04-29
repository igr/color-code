package ac.obl.cc.calc

import ac.obl.cc.ctx.Ctx

fun main() {

    // Examples of Context usage
    // This approach lead to creating the `Operands` class and `with` function

    Ctx.of(Operands.of(2, 3))
        .map { multiply(it) }
        .use { println(it) }

    Ctx.of(Operands.of(2, 3))
        .map { add(it) }
        .use { println(it) }

    Ctx.of(Operands.of(2, 3))
        .map(add)
        .use(printValue)

    Ctx.of(Operands.of(6, 2))
        .map(divide())
        .use { println(it) }

    Ctx.of(Operands.of(6, 2))
        .map(divide())
        .map(with(9))
        .map(add)
        .use(printValue)


    // Alternative syntax, more user-readable

    (Ctx
        + (Value(3))
        + with(5)
        + add
        + with(3)
        + add
        - printValue
    )

    // Assigning final context value to the variable

    val result = (
        Ctx
            + (Operands.of(2, 3))
            + add)()

    println(result)

}

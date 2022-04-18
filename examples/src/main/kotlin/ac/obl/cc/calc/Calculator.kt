package ac.obl.cc.calc

import ac.obl.cc.DATA
import ac.obl.cc.FUNCTION

/**
 * This is the domain.
 */

@DATA
data class Value(val number: Number, val isFloat: Boolean = number is Float || number is Double)

@DATA
data class Operands(val left: Value, val right: Value) {
    val isFloat = left.isFloat || right.isFloat     // aux property
    companion object {
        fun of(left: Value, right: Value): Operands {
            return Operands(left, right)
        }
        fun of(left: Number, right: Number): Operands {
            return Operands(Value(left), Value(right))
        }
    }
}

/**
 * Example of val reference to an anonymous function.
 */
@FUNCTION
val add: (Operands) -> Value = {
    if (it.isFloat) {
        Value(it.left.number.toDouble() + it.right.number.toDouble(), true)
    } else {
        Value(it.left.number.toLong() + it.right.number.toLong(), false)
    }
}

/**
 * Example of the object function.
 */
@FUNCTION
object subtract: (Operands) -> Value {
    override fun invoke(operands: Operands): Value {
        return if (operands.isFloat) {
            Value(operands.left.number.toDouble() - operands.right.number.toDouble(), true)
        } else {
            Value(operands.left.number.toLong() - operands.right.number.toLong(), false)
        }
    }
}

@FUNCTION
class divide: (Operands) -> Value {
    override fun invoke(operands: Operands): Value {
        return if (operands.isFloat) {
            Value(operands.left.number.toDouble() / operands.right.number.toDouble(), true)
        } else {
            if ((operands.left.number.toLong() % operands.right.number.toLong()) == 0L) {
                Value(operands.left.number.toLong() / operands.right.number.toLong(), false)
            } else {
                Value(operands.left.number.toDouble() / operands.right.number.toLong(), true)
            }

        }
    }
}

/**
 * Example of the _declared_ function that is _not_ a FUNCTION yet.
 * Once when it becomes an instance, it becomes a FUNCTION.
 */
fun multiply(operands: Operands): Value {
    return if (operands.isFloat) {
        Value(operands.left.number.toDouble() * operands.right.number.toDouble(), true)
    } else {
        Value(operands.left.number.toLong() * operands.right.number.toLong(), false)
    }
}

/**
 * Function for printing a value.
 */
@FUNCTION
val printValue: (Value) -> Unit = {
    println(it)
}

@FUNCTION
class with(private val right: Number): (Value) -> Operands {
    override fun invoke(left: Value): Operands {
        return Operands.of(left, Value(right))
    }

}

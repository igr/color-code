# ISCAN

This is a pen-and-paper tool for quick analysis of the code structure. The goal is to tell quickly if the code is relatively well-structured or not.

The ISCAN report is a distilled version of the code structure.

## Data

Data elements (DATA, but properties of the class, too) are simply listed.

For example:

```kotlin
data class Wallet(var cash: Int)
data class Customer(val wallet: Wallet)
```

is represented as:

```text
* 🟥 Wallet
* 🟦 Customer
```

`Wallet` is STATE because it's mutable. `Customer` is DATA because it is not mutable.

List all data types and determine if they are STATE or DATA (mutable or immutable).

## Functions, methods

Functions, however, require more attention. The template is the following:

```text
* (🟨 or 🟧) <function name>
arg:
<List of arguments. Implicit arguments are put in brackets.>
out:
<List of outputs. Implicit outputs are put in brackets.>
inv:
<List of invocation subjects. Basically, everything that is _left_ of the dot.
Each invocation subject is marked with R (read), W (write), C (create), and I (invocation).>  
use:
<List of used data. Basically, all data _right_ of the dot.>
```

The signs of **BAD** code:

+ ACTION functions are not OK.
+ implicit arguments are BAD.
+ implicit outputs are BAD.
+ invocation that is W are BAD (as it modifies external, mutable data).
+ invocation that is R is suspicious (as it reads external data).
+ abstraction leaks of the used data are BAD.

The last rule is a bit tricky. With the ISCAN, there are two tricks how to determine _possible_ abstraction leaks:

+ too many elements in `use` section
+ elements in `use` section are UNRELATED to method or input arguments _names_. Ask yourself: "does this method need to know about the ____".

## Class

ISCAN of the classes (in OOP) is simply a collection of ISCAN records of internal data and all its methods. Usually, class methods have implicit arguments and sometimes implicit outputs.  

## Examples

Super-trivial example:

```kotlin
fun add(a: Int, b: Int): Int {
    return a + b
}
```

```text
ISCAN
* 🟨 add
arg:
    - a
    - b
out:
    - sum
inv:
use:
```

OOP example that breaks LoD:

```kotlin
class Paperboy {
  private var collectedAmount: Int = 0
  fun collectMoney(customer : Customer, dueAmount: Int) {
    if (customer.wallet.cash < dueAmount) {
      throw IllegalStateException("Customer has insufficient funds")
    }
    customer.wallet.cash -= dueAmount
    collectedAmount += dueAmount
  }
}
```

```text
ISCAN

PAPERBOY
--------
* 🟥 collectedAmount
* 🟧 collectMoney
arg:
    - [collectedAmount]
    - Customer
    - Int
out: 
    - [collectedAmount]
inv:
    - customer (R)
    - wallet (R/W)
    - [this] (R/W)
use:
    - wallet
    - cash
    - collectedAmount
```

Above code is BAD:

- First, the internal state is mutable (STATE)
- The method has implicit argument and output, `collectedAmount`.
- The method changes the state of `wallet`.
- Abstraction leak of `wallet` in the method. Actors are `customer` and `paperboy`. They use `Int` for the amount of money. This method DOES NOT need to know about the `wallet` i.e. how the money is actually stored.

## ISCAN is a work in progress

Time needed.

## ISCAN is partial

You don't need to perform the ISCAN of the whole code. It is enough to scan only relevant parts; feel free to ignore the rest.

# ISCAN

This is a pen-and-paper tool for quick analysis of the code structure. The goal is to tell quickly if the code is relatively well-structured or not.

The ISCAN report is a distilled version of the code structure.

## Data

Data elements (DATA, but properties of the class, too) are listed.

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

`Wallet` is STATE because it is mutable. `Customer` is DATA because it is not mutable.

List all data types and determine if they are STATE or DATA (mutable or immutable).

## Functions, methods

Functions, however, require more attention. The template is the following:

```text
* (🟨 or 🟧) <function name>
arg:
  - <name>
out:
  - <name>
inv:
  - <name>[R|W|C|I]+
use:
  - <name>
```

Here is each section explained.

### ARG

List of arguments. _Implicit_ arguments are surrounded with brackets.

### OUT

List of outputs. _Implicit_ outputs are surrounded with brackets.

### INV

List of invocation subjects (without duplication).

A way to locate all invocation subjects is to find all the invocation dots. Invocation subject is everything that is _left_ of the dot.

Each invocation subject is marked with one of the following:

+ `R` - reads something from it, usually data
+ `W` - writes something into it, usually data
+ `C` - creates something, usually data
+ `I` - invocation of another entity

Example:

```kt
foo.bar(1, 2)
foo.baz.qux = 2
```

We have 3 invocation dots. Everything left is: `foo` (invocation), `foo` (read), `baz`(write).

```text
inv:
  - foo (I/R)
  - baz (W)
```
### USE

List of used data - or the access.

A way to locate all used data is find all the invocation dots. Everything _right_ of the dot is some kind of data/access.

In above example, right of the dots are: `bar`, `baz`, `qux`.

## The signs of **BAD** code

+ ACTION is not OK. However, sometimes you can not avoid using an ACTION - that is also OK. 
+ _implicit_ arguments are BAD.
+ _implicit_ outputs are BAD.
+ write invocation (`W`) is BAD (it directly modifies external, mutable data).
+ read invocation (`R`) needs to be checked - are we accessing more than we should (abstraction leak)? The `use` section helps detects this.
+ invocation that is `C` may be suspicious - do we create something that is not on the same abstraction level.
+ abstraction leaks are BAD.

(These rules need some more work, admittedly, but the gist is there.)

The last rule is a bit ambiguous. With the ISCAN, there are two tricks how to determine _possible_ abstraction leaks:

+ too many elements in `use` section. 
+ elements in `use` section are UNRELATED to method or input arguments _names_. Ask yourself: "does this function need to know about the ____".

## Bad Spreads

This is important. If you detect a bad entity, it spreads to all places where it is used.

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
- Abstraction leak of `wallet` in the method. Actors are `customer` and `paperboy`. They use `Int` for the amount of money. This method DOES NOT need to know about the `wallet` i.e. how the money is stored.

## ISCAN is a work in progress

Time needed.

## ISCAN is partial

You do not need to perform the ISCAN of the whole code. It is enough to scan only relevant parts; feel free to ignore the rest.

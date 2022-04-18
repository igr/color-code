# 🟦 DATA

+ DATA is an _immutable_ instance that holds only _data_.
+ DATA has _just_ properties. Some properties can aggregate others.
+ There are _no_ operations in DATA!

```kotlin
data class Point(val x: Int, val y: Int)
```

(Some languages already have syntax for DATA classes.)

> ⚡️ **Pro Tip**: Avoid primitive obsession. Minimize usage of common types for DATAs properties that you pass around.

## On construction

+ Create DATA using a single ctor.
+ You can have auxiliary static ctors (in the companion `object` or as a `static` method).
+ An `init` block or ctor code block may finish the data initialization.

## On naming

+ DATA name is a _noun_.
+ Avoid the use of internal types in the name.
 
For example, don't use `BooksList` but, e.g., `Books`. Avoid using `and` to concatenate two domain names; try to find a new domain name instead. `BookId` is OK, `BookUuid` tells about the nature of the `id`, which is part of the implementation.

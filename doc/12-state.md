# 🟥 STATE

+ STATE is long-lived mutable storage.
+ STATE is a side-effect.
+ STATE is contagious.

## As storage

STATE **never** expose the inner implementation of the storage! 

The STATE may store data using collection classes. Hence, STATE never exposes the collection type (e.g., `List`, `Map`); instead, it provides ways to _operate_ on its content.

## As side-effect

STATE is also a side-effect, like database, console, file system, sockets...

## On methods

+ STATE has methods that modify the state.
+ STATE exposes content.
+ STATE hides implementation.

Do not add operational methods to STATE.

> ⚡️ Pro tip: minimize the number of STATE methods.

# 🟧 Action

ACTION is a function that has STATE or another ACTION as an argument (_implicit_ or _explicit_).

Actions are not pure.

## Control the ACTIONs

Any FUNCTION that uses STATE becomes the ACTION itself. Any FUNCTION that uses ACTION becomes the ACTION itself. STATE is contagious.

You can control the spreading of the ACTIONs by having an _abstract barrier_ in the form of e.g. an interface:

```kotlin
object FooAction: (String) -> Number {
	// ACTION
}

// FUNCTION, bind the implementation
val fooAction: (String) -> Number = FooAction
```

You will often need ACTIONs because of 3rd party code and frameworks. That is OK.   

> ⚡️ Pro tip: Try hard to minimize the spread of the ACTIONs.

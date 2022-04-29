# Example: Calculator

The calculator provides basic math operation: `+`, `-`, `\`, `*` on input.

Each math operation is a FUNCTION that maps the input pair into the result. E.g. `add` takes an input tuple and maps it into the single result value.

> Domain keywords detected: input `tuple`, result `value`. 

We need DATA types that will represent both input and output.

Obviously, we need DATA types for the value: `Value`. This type holds a `Number`, but also a flag that indicates if the value is a float-point number or not.

Interesting is the input tuple naming. The first idea might be `ValueTuple`, or `ValuePair`. Now, here comes one potentially good side of having one single argument - it forces you to think about what is the actual context of a function. So, we are talking here about the `Operands`.    

> Focus on verbs to get FUNCTIONs.

This example is trivial. FUNCTIONs are: `add`, `subtract`...

## Calculation continuation

Calculation of `Operands` results with the single `Value` in the context. We need to bind it with another `Value` to form a new `Operands` instance.

Therefore, we need another context transformation i.e. verb i.e. FUNCTION.

Now things become interesting. The common way would be a function that takes 2 arguments:

```kotlin
  .map { Operands.of(it, 8) }
```

The streamlined approach is to have a FUNCTION that operates on the previous value (the context), using the configuration of the new value.  

```kotlin
  .map(with(8))
```

The `8` here is the _configuration_ argument, not the _input_! We use `with()` to create a FUNCTION configured with a number that works on the previous value, stored in Context. 
